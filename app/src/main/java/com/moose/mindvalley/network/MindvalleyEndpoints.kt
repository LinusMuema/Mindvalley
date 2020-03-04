package com.moose.mindvalley.network

import com.moose.mindvalley.models.Categories
import com.moose.mindvalley.models.Channels
import com.moose.mindvalley.models.NewEpisodes
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header

interface MindvalleyEndpoints {
    @GET("/z5AExTtw")
    fun getEpisodes(): Observable<ResponseBody>

    @GET("/Xt12uVhM")
    fun getChannels(): Observable<ResponseBody>

    @GET("/A0CgArX3")
    fun getCategories(): Observable<ResponseBody>
}