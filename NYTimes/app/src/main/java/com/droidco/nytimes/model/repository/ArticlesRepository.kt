package com.droidco.nytimes.model.repository

import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.local.ArticlesLocalDataSource
import com.droidco.nytimes.model.remote.ArticlesRemoteDataSource
import io.reactivex.Observable
import timber.log.Timber
import java.util.*

class ArticlesRepository() {

    fun fetchArticles(section: String): Observable<List<Article>> {
        return Observable.concatArray(
            fetchLocalArticles(section),
            fetchRemoteArticles(section)
        )
    }

    private fun fetchLocalArticles(section: String): Observable<List<Article>> {
        return ArticlesLocalDataSource.getArticles(section).filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Timber.d("Emitting ${it.size} articles from DB...")
            }
    }

    private fun fetchRemoteArticles(section: String): Observable<List<Article>> {

        return ArticlesRemoteDataSource.getArticles(section)
            .map {
                it.results ?: emptyList<Article>()
            }
            .doOnNext {
                Timber.d("Fetched ${it.size} articles from API...")
                it.forEach {article ->
                    article.section = section.toLowerCase(Locale.ROOT)
                }

                storeArticlesLocally(it)
            }
    }

    private fun storeArticlesLocally(articles: List<Article>) {
        ArticlesLocalDataSource.insertArticles(articles)
    }
}