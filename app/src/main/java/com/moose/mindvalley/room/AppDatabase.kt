package com.moose.mindvalley.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moose.mindvalley.models.*


@Database (entities = [DbEpisodes::class, DbCategories::class, DbChannels::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun dao(): MindvalleyDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Mindvalley"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}