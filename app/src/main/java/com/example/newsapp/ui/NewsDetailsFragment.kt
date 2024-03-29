package com.example.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.models.ArticleData
import com.example.newsapp.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private val viewModel: NewsViewModel by inject()
    val args = navArgs<NewsDetailsFragmentArgs>()
    private var currentArticleToSave: ArticleData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE

        val currentArticle = args.value
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(currentArticle.articleUrl)
            binding.progressBar.visibility = View.INVISIBLE
        }
        viewModel.articles.observe(viewLifecycleOwner) { article ->
            if (article != null) {
                val list = article.articles
                list.forEach {
                    if (it.url == args.value.articleUrl)
                        currentArticleToSave = it
                }
            }
        }
        binding.saveArticleBtn.setOnClickListener {
            if (currentArticleToSave!=null) {
                //Save Article
                viewModel.saveArticle(currentArticleToSave!!)
                Snackbar.make(
                    requireView(), "Article saved successfully", Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    requireView(), "Source isn't saved", Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}