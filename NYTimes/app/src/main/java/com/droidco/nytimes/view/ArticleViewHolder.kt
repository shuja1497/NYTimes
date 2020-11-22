package com.droidco.nytimes.view

import com.droidco.nytimes.databinding.ItemArticleBinding
import com.droidco.nytimes.model.data.Article

class ArticleViewHolder(private val binding: ItemArticleBinding) : BaseViewHolder(binding.root) {

    companion object {
        private const val TAG = "ArticleViewHolder"
    }

    fun bind(article: Article) {
        binding.article = article
    }

}