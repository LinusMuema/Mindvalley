package com.moose.mindvalley

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.models.DbChannels
import com.moose.mindvalley.models.DbEpisodes
import com.moose.mindvalley.room.AppDao
import com.moose.mindvalley.room.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidUnitTests {
    lateinit var db: AppDatabase
    lateinit var dao: AppDao
    private val context: Application = ApplicationProvider.getApplicationContext()

    @Before
    fun init(){
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        dao = db.dao()
    }

    @After
    fun clear(){
        db.close()
    }

    @Test
    fun testReadAndWriteEpisodes(){
        val testEpisodes = DbEpisodes(0, "This is an episode example")
        dao.insertEpisodes(testEpisodes).doOnComplete {
            dao.getEpisodes().test().assertValue(listOf(testEpisodes))
        }
    }

    @Test
    fun testReadAndWriteChannels(){
        val testChannel = DbChannels(0, "This is a channels example")
        dao.insertChannels(testChannel).doOnComplete {
            dao.getChannels().test().assertValue(listOf(testChannel))
        }
    }

    @Test
    fun testReadAndWriteCategories(){
        val testCategory = DbCategories(0, "This is a category example")
        dao.insertCategories(testCategory).doOnComplete {
            dao.getCategories().test().assertValue(listOf(testCategory))
        }
    }
}