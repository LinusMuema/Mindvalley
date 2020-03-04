package com.moose.mindvalley.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moose.mindvalley.models.Categories
import com.moose.mindvalley.models.Channels
import com.moose.mindvalley.models.NewEpisodes
import com.moose.mindvalley.network.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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
            .subscribe({},{}))
    }

    //Get available channels
    fun getChannels(){
        compositeDisposable.add(ServiceBuilder.buildService().getChannels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{}))
    }

    //Get all categories
    fun getCategories(){
        compositeDisposable.add(ServiceBuilder.buildService().getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{}))
    }
}