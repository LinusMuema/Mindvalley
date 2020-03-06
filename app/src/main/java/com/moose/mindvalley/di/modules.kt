package com.moose.mindvalley.di

import android.app.Application
import androidx.room.Room
import com.moose.mindvalley.network.MindvalleyEndpoints
import com.moose.mindvalley.network.ServiceBuilder
import com.moose.mindvalley.room.AppDatabase
import com.moose.mindvalley.room.AppRepository
import com.moose.mindvalley.viewmodels.MainActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

//Database and DAO modules
val dbModules = module {
    fun getDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "Mindvalley").build()
    }
    fun getDao(appDatabase: AppDatabase) = appDatabase.dao()

    single { getDatabase(application = androidApplication()) }
    single { getDao(appDatabase = get()) }
}

//Retrofit module
val networkModules = module {
    fun getRetrofit(): MindvalleyEndpoints {
        return ServiceBuilder.buildService()
    }
    single { getRetrofit() }
}

//One composite disposable for the whole application
val compositeModule = module {
    single { CompositeDisposable() }
}

//The application repository module
val repositoryModule = module {
    single { AppRepository(dao = get(), compositeDisposable = get()) }
}

//The viewmodel module with it's dependencies
val viewModelModule = module {
    viewModel {
        MainActivityViewModel(
            repository = get(),
            compositeDisposable = get(),
            retrofit = get()
        )
    }
}

