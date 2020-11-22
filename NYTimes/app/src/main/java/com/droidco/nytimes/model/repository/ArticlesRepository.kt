package com.droidco.nytimes.model.repository

import com.droidco.nytimes.model.data.ArticlesResponse
import com.droidco.nytimes.model.remote.ArticlesRemoteDataSource

class ArticlesRepository() {

    suspend fun fetchArticles(section: String): ArticlesResponse {
        return fetchRemoteArticles(section)
    }

    private suspend fun fetchRemoteArticles(section: String): ArticlesResponse {
        return ArticlesRemoteDataSource.getArticles(section)

    }
}