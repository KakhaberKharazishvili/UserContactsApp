package com.example.usercontactsapp.di

import com.example.usercontactsapp.features.splash.SplashViewModel
import com.example.usercontactsapp.features.useredit.UserEditViewModel
import com.example.usercontactsapp.features.userform.UserFormViewModel
import com.example.usercontactsapp.features.userinfo.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { UserFormViewModel(get()) }
    viewModel { UserEditViewModel(get()) }
    viewModel { UserInfoViewModel(get()) }

}