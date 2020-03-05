package com.moose.mindvalley.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes


@Database (entities = [DbEpisodes::class, DbCategories::class, DbChannels::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun dao(): MindvalleyDao
}