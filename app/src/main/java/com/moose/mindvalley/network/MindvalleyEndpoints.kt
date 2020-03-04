package com.moose.mindvalley.network

import com.moose.mindvalley.models.Categories
import com.moose.mindvalley.models.Channels
import com.moose.mindvalley.models.NewEpisodes
import io.reactivex.Observable
import retrofit2.http.GET

interface MindvalleyEndpoints {
    @GET("/z5AExTtw")
    fun getEpisodes(): Observable<NewEpisodes>

    @GET("/Xt12uVhM")
    fun getChannels(): Observable<Channels>

    @GET("/A0CgArX3")
    fun getCategories(): Observable<Categories>
}