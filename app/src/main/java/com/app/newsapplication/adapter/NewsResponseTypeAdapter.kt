package com.app.newsapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.newsapplication.R
import com.app.newsapplication.model.NewsResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_type_response.view.*

class NewsResponseTypeAdapter :
    RecyclerView.Adapter<NewsResponseTypeAdapter.NewsResponseViewHolder>() {

    inner class NewsResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //Used DiffUtil: So that it can update only the items that are updated and not the whole recyclerview list.
    private val diffUtilCallBack = object : DiffUtil.ItemCallback<NewsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: NewsResponseItem,
            newItem: NewsResponseItem
        ): Boolean {
            return oldItem.type.equals(newItem.type)
        }

        override fun areContentsTheSame(
            oldItem: NewsResponseItem,
            newItem: NewsResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsResponseViewHolder {
        return NewsResponseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_type_response,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsResponseViewHolder, position: Int) {
        val newsResponseItem = differ.currentList.get(position)

        holder.itemView.apply {
            btnItemType.text = newsResponseItem.type
            if(newsResponseItem.type==""){
                btnItemType.text="News"
            }
            btnItemType.setOnClickListener {
                onItemClickListener?.let { it(newsResponseItem) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((NewsResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsResponseItem?) -> Unit) {
        onItemClickListener = listener
    }
}