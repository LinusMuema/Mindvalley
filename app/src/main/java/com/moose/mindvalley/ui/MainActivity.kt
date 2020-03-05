package com.moose.mindvalley.ui

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
        viewModel.episodes.observe(this, Observer {
            Log.d("room_episodes", it.toString())
        })

        viewModel.channels.observe(this, Observer {
            Log.d("room_channels", it.toString())
        })

        viewModel.categories.observe(this, Observer {
            Log.d("room_categories", it.toString())
        })
        viewModel.getNetworkEpisodes()
        viewModel.getNetworkChannels()
        viewModel.getNetworkCategories()
    }
}
