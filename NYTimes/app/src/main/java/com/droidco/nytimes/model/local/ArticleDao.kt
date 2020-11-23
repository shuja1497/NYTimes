package com.droidco.nytimes.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droidco.nytimes.model.data.Article
import io.reactivex.Single

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

    @Query("SELECT * FROM article where section = :section")
    fun getAllArticlesBySection(section: String): Single<List<Article>>

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>

    @Query("delete from article where section = :section")
    fun deleteBySection(section: String)

    @Query("select * from article where article_id = :articleId")
    suspend fun getArticle(articleId: String): Article
}