package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.BuildConfig
import com.example.data.local.AppDatabase
import com.example.data.local.dao.ContactDao
import com.example.data.local.dao.UserDao
import com.example.data.remote.api.RandomUserApiService
import com.example.data.repository.datasource.LocalContactDataSourceImpl
import com.example.data.repository.datasource.RandomUserRemoteDataSourceImpl
import com.example.data.repository.impl.ContactRepositoryImpl
import com.example.data.repository.impl.UserRepositoryImpl
import com.example.data.repository.datasource.LocalContactDataSource
import com.example.data.repository.datasource.RandomUserRemoteDataSource
import com.example.domain.repository.ContactRepository
import com.example.domain.repository.UserRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Room.databaseBuilder(get<Application>(), AppDatabase::class.java, "user_contacts_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<UserDao> { get<AppDatabase>().userDao() }
    single<ContactDao> { get<AppDatabase>().contactDao() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<RandomUserApiService> {
        get<Retrofit>().create(RandomUserApiService::class.java)
    }

    single<LocalContactDataSource> { LocalContactDataSourceImpl(get()) }
    single<RandomUserRemoteDataSource> { RandomUserRemoteDataSourceImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ContactRepository> { ContactRepositoryImpl(get()) }
}
