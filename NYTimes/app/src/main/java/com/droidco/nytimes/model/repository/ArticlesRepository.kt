package com.droidco.nytimes.model.repository

import com.droidco.nytimes.di.ApplicationScope
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.local.ArticlesLocalDataSource
import com.droidco.nytimes.model.remote.ArticlesRemoteDataSource
import io.reactivex.Observable
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@ApplicationScope
class ArticlesRepository @Inject constructor(
    private val localDataSource: ArticlesLocalDataSource,
    private val remoteDataSource: ArticlesRemoteDataSource
) {

    fun fetchArticles(section: String, refreshCall: Boolean): Observable<List<Article>> {

        if (refreshCall) {
            return fetchRemoteArticles(section)
        }

        return Observable.concatArray(
            fetchLocalArticles(section),
            fetchRemoteArticles(section)
        )
    }

    private fun fetchLocalArticles(section: String): Observable<List<Article>> {
        return localDataSource.getArticles(section).filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Timber.d("Emitting ${it.size} articles from DB...")
            }
    }

    private fun fetchRemoteArticles(section: String): Observable<List<Article>> {

        return remoteDataSource.getArticles(section)
            .map {
                it.results ?: emptyList<Article>()
            }
            .doOnNext {
                Timber.d("Fetched ${it.size} articles from API...")
                it.forEach { article ->
                    article.section = section.toLowerCase(Locale.ROOT)
                }

                storeArticlesLocally(it)
            }
    }

    private fun storeArticlesLocally(articles: List<Article>) {
        localDataSource.insertArticles(articles)
    }

    suspend fun getArticleById(articleId: String): Article {
        return localDataSource.getArticle(articleId)
    }
}