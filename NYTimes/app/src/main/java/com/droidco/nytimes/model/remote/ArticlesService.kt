package com.droidco.nytimes.model.remote

import com.droidco.nytimes.model.data.Article
import com.droidco.nytimes.model.data.ArticlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesService {

    @GET("{section}.json")
    suspend fun getArticles(
        @Path("section") section: String,
        @Query("api-key") apiKey: String
    ): ArticlesResponse
}