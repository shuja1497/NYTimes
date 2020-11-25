package com.droidco.nytimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.droidco.nytimes.di.FragmentScope
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.repository.ArticlesRepository
import javax.inject.Inject

@FragmentScope
class ArticleDetailViewModel @Inject constructor(private val articlesRepository: ArticlesRepository) {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    suspend fun getArticle(articleId: String) {
        _article.value = articlesRepository.getArticleById(articleId)
    }

}