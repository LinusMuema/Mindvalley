package com.moose.mindvalley.network

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface MindvalleyEndpoints {
    @GET("/z5AExTtw")
    fun getEpisodes(): Observable<ResponseBody>

    @GET("/Xt12uVhM")
    fun getChannels(): Observable<ResponseBody>

    @GET("/A0CgArX3")
    fun getCategories(): Observable<ResponseBody>
}