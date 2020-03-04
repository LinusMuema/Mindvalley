package com.moose.mindvalley.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.moose.mindvalley.models.Categories
import com.moose.mindvalley.models.Channels
import com.moose.mindvalley.models.NewEpisodes
import com.moose.mindvalley.network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class MainActivityViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    //Episodes Live data
    private val episodes: MutableLiveData<NewEpisodes> by lazy {
        MutableLiveData<NewEpisodes>()
    }

    //Channels Live Data
    private val channels: MutableLiveData<Channels> by lazy {
        MutableLiveData<Channels>()
    }

    //Categories Live data
    private val categories: MutableLiveData<Categories> by lazy {
        MutableLiveData<Categories>()
    }

    //Get the Latest episodes
    fun getEpisodes(){
        compositeDisposable.add(ServiceBuilder.buildService().getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val episodes = Gson().fromJson(json, NewEpisodes::class.java)
            },{}))
    }

    //Get available channels
    fun getChannels(){
        compositeDisposable.add(ServiceBuilder.buildService().getChannels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val channels = Gson().fromJson(json, Channels::class.java)
            },{}))
    }

    //Get all categories
    fun getCategories(){
        compositeDisposable.add(ServiceBuilder.buildService().getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val categories = Gson().fromJson(json, Categories::class.java)
            },{}))
    }
}