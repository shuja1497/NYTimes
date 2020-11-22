package com.droidco.nytimes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.droidco.nytimes.R
import com.droidco.nytimes.databinding.ItemArticleBinding
import com.droidco.nytimes.databinding.ItemLoaderBinding
import com.droidco.nytimes.model.data.Article

class ArticleAdapter(private val list: ArrayList<Article>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val ITEM_VIEW_TYPE_LOADING = 0
        const val ITEM_VIEW_TYPE_ARTICLE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM_VIEW_TYPE_ARTICLE -> {
                val binding = DataBindingUtil.inflate<ItemArticleBinding>(
                    inflater,
                    R.layout.item_article, parent, false
                )
                ArticleViewHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ItemLoaderBinding>(
                    inflater,
                    R.layout.item_loader, parent, false
                )
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> {
                holder.bind(list[position])
            }
        }
    }

    fun addItems(items: List<Article>) {

        list.apply {
            clear()
            addAll(items)
        }
//        list.clear()
//        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return ITEM_VIEW_TYPE_ARTICLE
    }

    override fun getItemCount(): Int {
        return list.size
    }

}