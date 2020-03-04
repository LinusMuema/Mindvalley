package com.moose.mindvalley.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes


@Database (entities = [DbEpisodes::class, DbCategories::class, DbChannels::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun dao(): MindvalleyDao
    companion object{
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "mindvalley.db").build()
            }
            return instance!!
        }
    }
}