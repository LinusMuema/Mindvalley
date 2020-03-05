package com.moose.mindvalley.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.moose.mindvalley.R
import com.moose.mindvalley.viewmodels.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Observe episodes LiveData
        viewModel.episodes.observe(this, Observer {
            if (it != null){
                Log.d("room_episodes", it.toString())
            }
        })

        //Observe channels LiveData
        viewModel.channels.observe(this, Observer {
            if (it != null) {
                Log.d("room_channels", it.toString())
            }
        })

        //Observe categories LiveData
        viewModel.categories.observe(this, Observer {
            if (it != null) {
                Log.d("room_categories", it.toString())
            }
        })

        //Get data from the database
        getDbData()
    }

    private fun getDbData() {
        viewModel.getEpisodes()
        viewModel.getChannels()
        viewModel.getCategories()
        if (connectionAvailable())
            getNetworkData()
        else
            Log.d("room_network", "Network not Available")
    }

    private fun getNetworkData() {
        viewModel.getNetworkEpisodes()
        viewModel.getNetworkChannels()
        viewModel.getNetworkCategories()
    }

    private fun connectionAvailable(): Boolean {
        val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo
        return activeNetwork != null
    }
}
