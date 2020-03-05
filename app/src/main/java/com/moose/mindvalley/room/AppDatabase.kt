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
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Mindvalley.db"
            )
                .build()
    }
}