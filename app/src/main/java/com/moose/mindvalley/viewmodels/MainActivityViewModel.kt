package com.moose.mindvalley.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.moose.mindvalley.models.*
import com.moose.mindvalley.network.ServiceBuilder
import com.moose.mindvalley.room.AppDatabase
import com.moose.mindvalley.room.MindvalleyDao
import com.moose.mindvalley.room.MindvalleyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup


class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    var categories: LiveData<DbCategories>
    private var mindvalleyRepository: MindvalleyRepository

    init {
        val dao = AppDatabase.getDatabase(application).dao()
        mindvalleyRepository = MindvalleyRepository(dao)
        categories = mindvalleyRepository.dbCategories
    }
    //Get the Latest episodes
    fun getEpisodes() {
        compositeDisposable.add(ServiceBuilder.buildService().getEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbEpisodes = DbEpisodes(0, json)
                mindvalleyRepository.updateEpisodes(dbEpisodes)
            },{}))
    }

    //Get available channels
    fun getChannels() {
        compositeDisposable.add(ServiceBuilder.buildService().getChannels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbChannels = DbChannels(0, json)
                mindvalleyRepository.updateChannels(dbChannels)
            },{}))
    }

    //Get all categories
    fun getCategories() {
        compositeDisposable.add(ServiceBuilder.buildService().getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val json = Jsoup.parse(it.string()).select("textarea").text()
                val dbCategories = DbCategories(0, json)
                mindvalleyRepository.updateCategories(dbCategories)
            },{}))
    }

    override fun onCleared() {
        super.onCleared()
        //Dispose the composite to avoid memory leaks
        compositeDisposable.dispose()
    }
}