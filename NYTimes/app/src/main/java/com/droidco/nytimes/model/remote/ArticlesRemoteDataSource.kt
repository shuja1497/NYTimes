package com.droidco.nytimes.model.remote

import com.droidco.nytimes.model.data.ArticlesResponse
import com.droidco.nytimes.utils.API_KEY
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(private val articleApiService: ArticlesService) {

    fun getArticles(section: String): Observable<ArticlesResponse> {
        return articleApiService.getArticles(section, API_KEY)
    }
}