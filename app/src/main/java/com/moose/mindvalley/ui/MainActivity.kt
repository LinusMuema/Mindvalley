package com.moose.mindvalley.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.moose.mindvalley.R
import com.moose.mindvalley.room.AppDatabase
import com.moose.mindvalley.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getEpisodes()
        viewModel.getChannels()
        viewModel.getCategories()
        viewModel.categories.observe(this, Observer {
            Log.d("viewmodel_", "$it")
        })
    }
}
