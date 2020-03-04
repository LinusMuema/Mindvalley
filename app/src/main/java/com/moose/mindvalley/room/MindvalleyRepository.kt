package com.moose.mindvalley.room

import androidx.lifecycle.LiveData
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes

class MindvalleyRepository(private val dao: MindvalleyDao){

    val dbCategories: LiveData<DbCategories> = dao.getCategories()

    fun updateCategories(categories: DbCategories) {
        val saved  = dao.insertCategories(categories)
    }

    fun updateChannels(channels: DbChannels) {
        dao.insertChannels(channels)
    }

    fun updateEpisodes(episodes: DbEpisodes) {
        dao.insertEpisodes(episodes)
    }
}