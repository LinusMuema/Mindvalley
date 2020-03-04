package com.moose.mindvalley.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes

class MindvalleyRepository(private val dao: MindvalleyDao){

    fun getEpisodes(): LiveData<DbEpisodes>{
        val episodeLiveData = dao.getEpisodes()
        Log.d("room_", "${episodeLiveData.value}")
        return episodeLiveData
    }

    fun updateCategories(categories: DbCategories) {
        dao.insertCategories(categories)
    }

    fun updateChannels(channels: DbChannels) {
        dao.insertChannels(channels)
    }
    fun updateEpisodes(episodes: DbEpisodes) {
        dao.insertEpisodes(episodes)
    }
}