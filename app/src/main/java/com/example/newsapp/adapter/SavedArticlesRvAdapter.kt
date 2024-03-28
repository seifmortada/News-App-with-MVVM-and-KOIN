package com.example.newsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemHeadlinesRvBinding
import com.example.newsapp.databinding.ItemSourcesRvBinding
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.Source
import com.example.newsapp.models.SourceData
import com.example.newsapp.ui.HomeFragmentDirections
import com.example.newsapp.ui.SavedArticlesFragmentDirections
import com.squareup.picasso.Picasso

class SavedArticlesRvAdapter : RecyclerView.Adapter<SavedArticlesRvAdapter.MyViewHolder>() {

    private var articleList = emptyList<ArticleData>()

    inner class MyViewHolder(val binding: ItemHeadlinesRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemHeadlinesRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = articleList[position]
        holder.binding.apply {
            Picasso.get().load(currentItem.urlToImage).into(articleImg)
            articleTitle.text = currentItem.title
            newsDesciption.text = currentItem.description
        }
        holder.itemView.setOnClickListener {
            val action =
                SavedArticlesFragmentDirections.actionSavedArticlesFragmentToNewsDetailsFragment(currentItem.url)
            it.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<ArticleData>) {
        articleList = list
        notifyDataSetChanged()
    }
}