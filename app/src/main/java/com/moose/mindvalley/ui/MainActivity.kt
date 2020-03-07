package com.moose.mindvalley.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moose.mindvalley.R
import com.moose.mindvalley.adapters.EpisodeListAdapter
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
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        //Observe episodes LiveData
        viewModel.episodes.observe(this, Observer {
            if (it.isNotEmpty()){
                val episodes = Gson().fromJson(it[0].episodes, Episodes::class.java)
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
                Log.d("room_channels", it.toString())
            }
        })

        //Observe categories LiveData
        viewModel.categories.observe(this, Observer {
            if (it.isNotEmpty()) {
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
