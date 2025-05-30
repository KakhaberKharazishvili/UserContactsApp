package com.example.usercontactsapp.di

import android.app.Application
import androidx.room.Room
import com.example.usercontactsapp.data.api.RandomUserApiService
import com.example.usercontactsapp.data.local.AppDatabase
import com.example.usercontactsapp.data.local.ContactDao
import com.example.usercontactsapp.data.local.UserDao
import com.example.usercontactsapp.data.repository.UserRepository
import com.example.usercontactsapp.data.repository.UserRepositoryImpl
import com.example.usercontactsapp.data.repository.ContactRepository
import com.example.usercontactsapp.data.repository.ContactRepositoryImpl
import com.example.usercontactsapp.data.repository.datasource.LocalContactDataSource
import com.example.usercontactsapp.data.repository.datasource.LocalContactDataSourceImpl
import com.example.usercontactsapp.data.repository.datasource.RandomUserRemoteDataSource
import com.example.usercontactsapp.data.repository.datasource.RandomUserRemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.usercontactsapp.BuildConfig

val dataModule = module {

    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "user_contacts_db")
            .fallbackToDestructiveMigration().build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }
    single<ContactDao> { get<AppDatabase>().contactDao() }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single<RandomUserApiService> { get<Retrofit>().create(RandomUserApiService::class.java) }

    single<LocalContactDataSource> { LocalContactDataSourceImpl(get()) }
    single<RandomUserRemoteDataSource> { RandomUserRemoteDataSourceImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ContactRepository> { ContactRepositoryImpl(get()) }
}
