package com.moose.mindvalley

import com.moose.mindvalley.network.MindvalleyEndpoints
import com.moose.mindvalley.network.ServiceBuilder
import io.reactivex.observers.TestObserver
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class NetworkUnitTests{

    lateinit var retrofit: MindvalleyEndpoints
    lateinit var observer: TestObserver<ResponseBody>

    @Before
    fun init(){
        retrofit = ServiceBuilder.buildService()
    }

    @After
    fun clear(){

    }

    @Test
    fun testGetNetworkEpisodes(){
        observer = retrofit.getEpisodes().test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
    }

    @Test
    fun testGetNetworkChannels(){
        observer = retrofit.getChannels().test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
    }

    @Test
    fun testGetNetworkCategories(){
        observer = retrofit.getCategories().test()
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
    }
}