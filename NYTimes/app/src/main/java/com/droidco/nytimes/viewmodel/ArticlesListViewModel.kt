package com.droidco.nytimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.droidco.nytimes.di.ApplicationScope
import com.droidco.nytimes.di.FragmentScope
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.data.ArticleResponse
import com.droidco.nytimes.model.repository.ArticlesRepository
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

@FragmentScope
class ArticlesListViewModel @Inject constructor(private val articlesRepository: ArticlesRepository) {

    private val _articlesList = MutableLiveData<List<Article>>()
    val articlesList: LiveData<List<Article>> = _articlesList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun getArticles(section: String, refreshCall: Boolean): Observable<ArticleResponse> {

        return articlesRepository.fetchArticles(section, refreshCall)
            .map {
                Timber.d("Mapping users to data...${it.size}")
                ArticleResponse(it, "Top 10 Users")
            }
            .onErrorReturn {
                Timber.d("An error occurred $it")
                ArticleResponse(emptyList(), "An error occurred", it)
            }
    }
}