package com.droidco.nytimes.model.remote

import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.data.ArticlesResponse
import com.droidco.nytimes.utils.API_KEY
import io.reactivex.Observable
import io.reactivex.Single

object ArticlesRemoteDataSource {

    private var service: ArticlesService? = null

    private fun getService(): ArticlesService {
        if (service == null) {
            synchronized(ArticlesService::class.java) {
                if (service == null) {
                    service = ApiClient.getService(ArticlesService::class.java)
                }
            }
        }
        return service!!
    }

    fun getArticles(section: String): Observable<ArticlesResponse> {
        return getService().getArticles(section, API_KEY)
    }
}