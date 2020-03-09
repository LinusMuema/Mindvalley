package com.moose.mindvalley.room

import androidx.lifecycle.MutableLiveData
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AppRepository(private val dao: AppDao, private val compositeDisposable: CompositeDisposable){

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
    fun getEpisodes(): MutableLiveData<List<DbEpisodes>> {
        //Episodes LiveData variable
        val episodes: MutableLiveData<List<DbEpisodes>> by lazy {
            MutableLiveData<List<DbEpisodes>>()
        }

        compositeDisposable.add(dao.getEpisodes()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                episodes.value  = it
            }, {}))

        return episodes
    }

    //Save channels to DB
    fun saveChannels(dbChannels: DbChannels) {
        compositeDisposable.add(dao.insertChannels(dbChannels)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getChannels()
            }, {}))
    }


    //Get the channels from DB
    fun getChannels(): MutableLiveData<List<DbChannels>> {
        //channels LiveData variables
        val channels: MutableLiveData<List<DbChannels>> by lazy {
            MutableLiveData<List<DbChannels>>()
        }

        compositeDisposable.add(dao.getChannels()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                channels.value = it
            }, {}))

        return channels
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

    //Get categories from DB
    fun getCategories(): MutableLiveData<List<DbCategories>> {
        //categories LiveData variable
        val categories: MutableLiveData<List<DbCategories>> by lazy {
            MutableLiveData<List<DbCategories>>()
        }

        compositeDisposable.add(dao.getCategories()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                categories.value = it
            },{}))

        return categories
    }
}