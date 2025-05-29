package com.example.usercontactsapp.di

import com.example.usercontactsapp.presentation.features.contactadd.ContactAddViewModel
import com.example.usercontactsapp.presentation.features.contactinfo.ContactInfoViewModel
import com.example.usercontactsapp.presentation.features.contactlist.ContactListViewModel
import com.example.usercontactsapp.presentation.features.splash.SplashViewModel
import com.example.usercontactsapp.presentation.features.useredit.UserEditViewModel
import com.example.usercontactsapp.presentation.features.userform.UserFormViewModel
import com.example.usercontactsapp.presentation.features.userinfo.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { UserFormViewModel(get()) }
    viewModel { UserEditViewModel(get()) }
    viewModel { UserInfoViewModel(get()) }
    viewModel {
        ContactAddViewModel(
            get(), get()
        )
    }
    viewModel { ContactListViewModel(get()) }
    viewModel { (contactId: Int) -> ContactInfoViewModel(contactId, get()) }
}