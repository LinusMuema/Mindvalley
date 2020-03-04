package com.moose.mindvalley.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moose.mindvalley.R
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.episodes.observe(this, Observer {
            if (it != null){
                Log.d("room_", "$it")
            }
            else{
                viewModel.getEpisodes()
                viewModel.getChannels()
                viewModel.getCategories()
            }
        })
    }
}
