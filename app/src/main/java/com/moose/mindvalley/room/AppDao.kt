package com.moose.mindvalley.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.moose.mindvalley.models.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface AppDao {

    @Query("SELECT * FROM db_episodes")
    fun getEpisodes(): Flowable<List<DbEpisodes>>

    @Query("SELECT * FROM db_channels")
    fun getChannels(): Flowable<List<DbChannels>>

    @Query("SELECT * FROM db_categories")
    fun getCategories(): Flowable<List<DbCategories>>

    @Insert(onConflict = REPLACE)
    fun insertEpisodes(episodes: DbEpisodes): Completable

    @Insert(onConflict = REPLACE)
    fun insertChannels(channels: DbChannels): Completable

    @Insert(onConflict = REPLACE)
    fun insertCategories(categories: DbCategories): Completable

}