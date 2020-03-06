package com.moose.mindvalley.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ServiceBuilder {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val retrofit = Retrofit.Builder()
    .baseUrl("https://pastebin.com/raw/")
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(client)
    .build()
    .create(MindvalleyEndpoints::class.java)

    fun buildService(): MindvalleyEndpoints {
        return retrofit
    }
}