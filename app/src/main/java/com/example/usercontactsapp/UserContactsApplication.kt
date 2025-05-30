package com.example.usercontactsapp

import android.app.Application
import com.example.usercontactsapp.presentation.features.di.viewModelModule
import com.example.data.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class UserContactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UserContactsApplication)
            modules(
                dataModule, viewModelModule
            )
        }
    }
}