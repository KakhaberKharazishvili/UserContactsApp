package com.example.usercontactsapp

import android.app.Application
import com.example.usercontactsapp.di.dataModule
import com.example.usercontactsapp.di.viewModelModule
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
