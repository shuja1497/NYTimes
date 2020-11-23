package com.droidco.nytimes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.local.ArticlesLocalDataSource
import kotlinx.coroutines.launch

class ArticleDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article

    fun getArticle(articleId: String) {

        viewModelScope.launch {
            _article.value = ArticlesLocalDataSource.getArticle(articleId)
        }
    }

}