package com.example.usercontactsapp.features.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
data object UserForm : AppDestination

@Serializable
data object UserInfo : AppDestination

@Serializable
data object UserEdit : AppDestination

@Serializable
data object ContactList : AppDestination

@Serializable
data object ContactAdd : AppDestination

@Serializable
data class ContactInfo(val contactId: Int) : AppDestination