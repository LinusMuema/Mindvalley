package com.moose.mindvalley.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moose.mindvalley.models.*
import com.moose.mindvalley.network.MindvalleyEndpoints
import com.moose.mindvalley.room.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup


class MainActivityViewModel(
    private val repository: AppRepository,
    private val compositeDisposable: CompositeDisposable,
    private val retrofit: MindvalleyEndpoints
): ViewModel() {



    //Get episodes from DB
    fun getEpisodes(): MutableLiveData<List<DbEpisodes>> {
        return repository.getEpisodes()
    }

    //Get channels from DB
    fun getChannels(): MutableLiveData<List<DbChannels>> {
        return repository.getChannels()
    }

    //Get categories from DB
    fun getCategories(): MutableLiveData<List<DbCategories>> {
        return repository.getCategories()
    }

    //Get the Latest episodes form api
    fun getNetworkEpisodes() {
        compositeDisposable.add(retrofit.getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbEpisodes = DbEpisodes(0, json)
                repository.saveEpisodes(dbEpisodes)
            },{}))
    }

    //Get available channels from api
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

    //Get all categories for api
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