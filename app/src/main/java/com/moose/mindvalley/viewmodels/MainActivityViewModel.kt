package com.moose.mindvalley.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.moose.mindvalley.models.*
import com.moose.mindvalley.network.MindvalleyEndpoints
import com.moose.mindvalley.room.MindvalleyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup


class MainActivityViewModel(
    private val repository: MindvalleyRepository,
    private val compositeDisposable: CompositeDisposable,
    private val retrofit: MindvalleyEndpoints
): ViewModel() {

    //Get the Latest episodes
    fun getNetworkEpisodes() {
        compositeDisposable.add(retrofit.getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("room_", "Network retrieval success")
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbEpisodes = DbEpisodes(0, json)
                repository.saveEpisodes(dbEpisodes)
            },{}))
    }

    //Get available channels
    fun getNetworkChannels() {
        compositeDisposable.add(retrofit.getChannels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbChannels = DbChannels(0, json)
                repository.saveChannels(dbChannels)
            },{}))
    }

    //Get all categories
    fun getNetworkCategories() {
        compositeDisposable.add(retrofit.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbCategories = DbCategories(0, json)
                repository.saveCategories(dbCategories)
            },{}))
    }

    override fun onCleared() {
        super.onCleared()
        //Dispose the composite to avoid memory leaks
        compositeDisposable.dispose()
    }
}