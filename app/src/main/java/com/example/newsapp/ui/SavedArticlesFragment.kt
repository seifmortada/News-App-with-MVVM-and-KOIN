package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newsapp.adapter.SavedArticlesRvAdapter
import com.example.newsapp.databinding.FragmentSavedArticlesBinding
import com.example.newsapp.viewmodel.NewsViewModel
import org.koin.android.ext.android.inject

class SavedArticlesFragment : Fragment() {
    private lateinit var binding: FragmentSavedArticlesBinding
    private lateinit var adapterArticles: SavedArticlesRvAdapter
    private val viewModel: NewsViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerViews()

        viewModel.savedArticles.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "There are no saved articles", Toast.LENGTH_SHORT)
                    .show()
            } else {
                adapterArticles.setData(it)
            }
        }
    }

    private fun initializeRecyclerViews() {
        adapterArticles = SavedArticlesRvAdapter()
        binding.headlinesRv.apply {
            adapter = adapterArticles
        }
    }


}