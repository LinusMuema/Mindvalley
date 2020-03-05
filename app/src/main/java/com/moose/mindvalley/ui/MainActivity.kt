package com.moose.mindvalley.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moose.mindvalley.R
import com.moose.mindvalley.viewmodels.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getNetworkEpisodes()
        viewModel.getNetworkChannels()
        viewModel.getNetworkCategories()
    }
}
