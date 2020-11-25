package com.droidco.nytimes.di.module

import com.droidco.nytimes.di.ApplicationScope
import com.droidco.nytimes.model.remote.ArticlesService
import com.droidco.nytimes.utils.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    private var retrofit = builder.build()
    private val httpClient = OkHttpClient.Builder()
    private val logging = run {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @ApplicationScope
    @Provides
    fun provideArticleApiService(
    ): ArticlesService {

        if (!httpClient.interceptors().contains(
                logging
            )
        ) {
            httpClient.addInterceptor(
                logging
            )
            builder.client(
                httpClient.build()
            )
            retrofit = builder.build()
        }

        return retrofit.create(ArticlesService::class.java)
    }
}
