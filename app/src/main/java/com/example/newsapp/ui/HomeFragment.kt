package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.ArticlesRvAdapter
import com.example.newsapp.adapter.SourcesRvAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.utils.Constants.TAG
import com.example.newsapp.viewmodel.NewsViewModel
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NewsViewModel by inject()
    private lateinit var sourceAdapter: SourcesRvAdapter
    private lateinit var articlesAdapter: ArticlesRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerViews()

        binding.savedArticles.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_savedArticlesFragment)
        }

        showLoading()
        viewModel.sources.observe(viewLifecycleOwner) {
            if (it != null) {
                hideLoadingSource()
                sourceAdapter.setData(it.sources)
                Log.e(TAG, "Sources Are ${it.sources.size}")
            } else {
                Log.e(TAG, "onViewCreated: Sources Are null")
            }
        }
        viewModel.articles.observe(viewLifecycleOwner) {
            if (it != null) {
                hideLoadingArticle()
                articlesAdapter.setData(it.articles)
                Log.e(TAG, "Articles Are ${it.totalResults}")

            } else {
                Log.e(TAG, "onViewCreated: Articles Are null")
            }
        }

    }

    private fun hideLoadingArticle() {
        binding.progressBarArticle.visibility = View.INVISIBLE
    }

    private fun hideLoadingSource() {
        binding.progressBarSource.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.apply {
            progressBarArticle.visibility = View.VISIBLE
            progressBarSource.visibility = View.VISIBLE
        }
    }

    private fun initializeRecyclerViews() {
        articlesAdapter = ArticlesRvAdapter()
        sourceAdapter = SourcesRvAdapter()

        binding.sourcesRv.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
            adapter = sourceAdapter
        }
        binding.headlinesRv.apply {
            adapter = articlesAdapter
        }
    }
}

