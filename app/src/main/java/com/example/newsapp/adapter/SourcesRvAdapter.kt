package com.example.newsapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemSourcesRvBinding
import com.example.newsapp.models.SourceData
import com.example.newsapp.ui.HomeFragmentDirections
import com.example.newsapp.utils.Constants

class SourcesRvAdapter : RecyclerView.Adapter<SourcesRvAdapter.MyViewHolder>() {

    private var sourceList = emptyList<SourceData>()

    inner class MyViewHolder(val binding: ItemSourcesRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        ItemSourcesRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = sourceList[position]
        if (currentItem.url == "null") {
            Log.e(Constants.TAG, "url is null ")
        } else {
        holder.binding.apply {
            newsName.text = currentItem.name
            newsCategory.text = currentItem.category
            newsLanguage.text = currentItem.language
            newsDescription.text = currentItem.description
        }
        holder.itemView.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(currentItem.url)
                it.findNavController().navigate(action)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<SourceData>) {
        sourceList = list
        notifyDataSetChanged()
    }
}