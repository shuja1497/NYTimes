package com.droidco.nytimes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.repository.ArticlesRepository

class ArticlesListViewModel(private val articlesRepository: ArticlesRepository) : ViewModel() {

    companion object {
        private const val TAG = "ArticlesListViewModel"
    }

    private val _articlesList = MutableLiveData<List<Article>>()
    val articlesList: LiveData<List<Article>> = _articlesList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    suspend fun getArticles(section: String) {

        _loading.value = true
        _error.value = false

        try {
            val articlesResponse = articlesRepository.fetchArticles(section)
            articlesResponse.results.let {
                _loading.value = false
                if (it != null) {
                    _error.value = false
                    _articlesList.value = it
                } else {
                    _error.value = true
                }
            }
        } catch (exception: Exception) {
            _loading.value = false
            _error.value = true
        }

    }
}