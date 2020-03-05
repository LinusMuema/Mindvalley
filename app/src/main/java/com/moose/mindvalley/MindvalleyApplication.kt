package com.moose.mindvalley

import android.app.Application
import com.moose.mindvalley.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MindvalleyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MindvalleyApplication)
            modules(listOf(dbModules, networkModules, viewModelModule, repositoryModule, compositeModule))
        }
    }
}