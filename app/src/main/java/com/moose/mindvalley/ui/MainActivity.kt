package com.moose.mindvalley.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.facebook.stetho.Stetho
import com.moose.mindvalley.R
import com.moose.mindvalley.models.DbCategories
import com.moose.mindvalley.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getNetworkEpisodes()
        viewModel.getNetworkChannels()
        viewModel.getNetworkCategories()
    }
}
