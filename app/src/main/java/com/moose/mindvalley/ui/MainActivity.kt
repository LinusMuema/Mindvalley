package com.moose.mindvalley.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moose.mindvalley.R
import com.moose.mindvalley.adapters.CategoryListAdapter
import com.moose.mindvalley.adapters.ChannelListAdapter
import com.moose.mindvalley.adapters.EpisodeListAdapter
import com.moose.mindvalley.models.Categories
import com.moose.mindvalley.models.Channels
import com.moose.mindvalley.models.Episodes
import com.moose.mindvalley.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Hide the status and navigation bars
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }

        //Observe the swiperefresh layout
        swipe_refresh.setOnRefreshListener {
            getDbData()
        }

        //Observe episodes LiveData
        viewModel.episodes.observe(this, Observer {
            if (it.isNotEmpty()){
                val episodes = Gson().fromJson(it[0].episodes, Episodes::class.java)
                swipe_refresh.isRefreshing = false
                episodes_recycler.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                    adapter = EpisodeListAdapter(episodes.data.media)
                }
            }
        })

        //Observe channels LiveData
        viewModel.channels.observe(this, Observer {
            if (it.isNotEmpty()) {
                swipe_refresh.isRefreshing = false
                val channels = Gson().fromJson(it[0].channels, Channels::class.java)
                channels_recycler.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ChannelListAdapter(channels.data.channels)
                }
            }
        })

        //Observe categories LiveData
        viewModel.categories.observe(this, Observer {
            if (it.isNotEmpty()) {
                swipe_refresh.isRefreshing = false
                val categories = Gson().fromJson(it[0].categories, Categories::class.java)
                categories_recycler.apply {
                    setHasFixedSize(true)
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter = CategoryListAdapter(categories.data.categories)
                }
            }
        })
        getDbData()
    }

    private fun getDbData() {
        viewModel.getEpisodes()
        viewModel.getChannels()
        viewModel.getCategories()
        if (connectionAvailable())
            getNetworkData()
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
