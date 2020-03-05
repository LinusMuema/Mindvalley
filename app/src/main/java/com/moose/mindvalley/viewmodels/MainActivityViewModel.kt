package com.moose.mindvalley.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.moose.mindvalley.models.*
import com.moose.mindvalley.network.ServiceBuilder
import com.moose.mindvalley.room.AppDatabase
import com.moose.mindvalley.room.MindvalleyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup


class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private var  appDatabase: AppDatabase = AppDatabase.getInstance(application)
    private var mindvalleyRepository: MindvalleyRepository

    init {
        mindvalleyRepository = MindvalleyRepository(appDatabase.dao(), compositeDisposable)
    }
    //Get the Latest episodes
    fun getNetworkEpisodes() {
        compositeDisposable.add(ServiceBuilder.buildService().getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("room_", "Netwrok retrieval success")
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbEpisodes = DbEpisodes(0, json)
                mindvalleyRepository.saveEpisodes(dbEpisodes)
            },{}))
    }

    //Get available channels
    fun getNetworkChannels() {
        compositeDisposable.add(ServiceBuilder.buildService().getChannels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbChannels = DbChannels(0, json)
                mindvalleyRepository.saveChannels(dbChannels)
            },{}))
    }

    //Get all categories
    fun getNetworkCategories() {
        compositeDisposable.add(ServiceBuilder.buildService().getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbCategories = DbCategories(0, json)
                mindvalleyRepository.saveCategories(dbCategories)
            },{}))
    }

    override fun onCleared() {
        super.onCleared()
        //Dispose the composite to avoid memory leaks
        compositeDisposable.dispose()
    }
}