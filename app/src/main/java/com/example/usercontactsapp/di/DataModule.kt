package com.example.usercontactsapp.di

import android.app.Application
import androidx.room.Room
import com.example.usercontactsapp.data.local.AppDatabase
import com.example.usercontactsapp.data.local.UserDao
import org.koin.dsl.module
import com.example.usercontactsapp.data.repository.UserRepository
import com.example.usercontactsapp.data.repository.UserRepositoryImpl

val dataModule = module {
    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "user_contacts_db")
            .fallbackToDestructiveMigration().build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get()) }
}
