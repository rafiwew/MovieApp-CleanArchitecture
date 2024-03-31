package com.piwew.movieapp_cleanarchitecture

import android.app.Application
import com.piwew.movieapp_cleanarchitecture.core.di.databaseModule
import com.piwew.movieapp_cleanarchitecture.core.di.networkModule
import com.piwew.movieapp_cleanarchitecture.core.di.repositoryModule
import com.piwew.movieapp_cleanarchitecture.di.useCaseModule
import com.piwew.movieapp_cleanarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}