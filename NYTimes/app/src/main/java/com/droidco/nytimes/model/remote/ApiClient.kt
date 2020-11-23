package com.droidco.nytimes.model.remote

import com.droidco.nytimes.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

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

    fun <S> getService(
        serviceClass: Class<S>
    ): S {

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

        return retrofit.create(serviceClass)
    }
}
