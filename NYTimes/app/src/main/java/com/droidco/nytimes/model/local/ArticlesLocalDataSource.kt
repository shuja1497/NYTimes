package com.droidco.nytimes.model.local

import android.annotation.SuppressLint
import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.utils.ApplicationController
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

object ArticlesLocalDataSource {

    private var articleDao: ArticleDao =
        ArticleDatabase(ApplicationController.instance).articleDao()

    fun getArticles(section: String): Single<List<Article>> {
        Timber.d("ARTICLE DAO >>>> $articleDao")
        return articleDao.getAllArticlesBySection(section.toLowerCase(Locale.ROOT))
    }

    @SuppressLint("CheckResult")
    fun insertArticles(list: List<Article>) {
        Timber.d("Inserting in DB")

        Observable.fromCallable { articleDao.insertAll(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${list.size} articles in DB...")
            }

    }

}