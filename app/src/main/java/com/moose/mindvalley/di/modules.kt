package com.moose.mindvalley.di

import android.app.Application
import androidx.room.Room
import com.moose.mindvalley.network.MindvalleyEndpoints
import com.moose.mindvalley.network.ServiceBuilder
import com.moose.mindvalley.room.AppDatabase
import com.moose.mindvalley.room.MindvalleyRepository
import com.moose.mindvalley.viewmodels.MainActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModules = module {
    fun getDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "Mindvalley").build()
    }
    fun getDao(appDatabase: AppDatabase) = appDatabase.dao()

    single { getDatabase(application = androidApplication()) }
    single { getDao(appDatabase = get()) }
}

val networkModules = module {
    fun getRetrofit(): MindvalleyEndpoints {
        return ServiceBuilder.buildService()
    }
    single { getRetrofit() }
}

val compositeModule = module {
    single { CompositeDisposable() }
}

val repositoryModule = module {
    single { MindvalleyRepository(dao = get(), compositeDisposable = get()) }
}

val viewModelModule = module {
    viewModel {
        MainActivityViewModel(
            repository = get(),
            compositeDisposable = get(),
            retrofit = get()
        )
    }
}

