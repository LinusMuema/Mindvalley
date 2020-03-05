package com.moose.mindvalley.room

import android.util.Log
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.definition.BeanDefinition

class MindvalleyRepository(private val dao: MindvalleyDao, private val compositeDisposable: CompositeDisposable){

    //Save episodes to DB
    fun saveEpisodes(dbEpisodes: DbEpisodes) {
        compositeDisposable.add(dao.insertEpisodes(dbEpisodes)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getEpisodes()
            }, {}))
    }

    //Get episodes from DB
    fun getEpisodes() {
        compositeDisposable.add(dao.getEpisodes()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("room_episodes", it.toString())
            }, {}))
    }

    fun saveChannels(dbChannels: DbChannels) {
        compositeDisposable.add(dao.insertChannels(dbChannels)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getChannels()
            }, {}))
    }


    //Get the channels from DB
    fun getChannels() {
        compositeDisposable.add(dao.getChannels()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("room_channels", it.toString())
            }, {}))
    }

    //Save categories to db
    fun saveCategories(dbCategories: DbCategories) {
        compositeDisposable.add(dao.insertCategories(dbCategories)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getCategories()
            }, {}))
    }

    fun getCategories() {
        compositeDisposable.add(dao.getCategories()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("room_categories", it.toString())
            },{}))
    }
}