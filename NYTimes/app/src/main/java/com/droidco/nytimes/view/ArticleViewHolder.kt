package com.droidco.nytimes.view

import android.view.View
import androidx.navigation.Navigation
import com.droidco.nytimes.databinding.ItemArticleBinding
import com.droidco.nytimes.model.data.Article

class ArticleViewHolder(private val binding: ItemArticleBinding) : BaseViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.article = article
        binding.card = this
    }

    fun onArticleClick(v: View, article: Article) {
        val action = ArticlesListFragmentDirections.actionNavigationListToArticleDetailsFragment(
            article.getArticleId()
        )
        Navigation.findNavController(v).navigate(action)
    }
}