package com.moose.mindvalley.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.moose.mindvalley.models.*

@Dao
interface MindvalleyDao {

    @Query("SELECT * FROM db_episodes")
    fun getEpisodes(): LiveData<DbEpisodes>

    @Query("SELECT * FROM db_channels")
    fun getChannels(): LiveData<DbChannels>

    @Query("SELECT * FROM db_categories")
    fun getCategories(): LiveData<DbCategories>

    @Insert(onConflict = REPLACE)
    fun insertEpisodes(episodes: DbEpisodes)

    @Insert(onConflict = REPLACE)
    fun insertChannels(channels: DbChannels)

    @Insert(onConflict = REPLACE)
    fun insertCategories(categories: DbCategories)

}