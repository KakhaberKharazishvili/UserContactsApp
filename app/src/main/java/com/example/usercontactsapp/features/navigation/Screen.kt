package com.example.usercontactsapp.features.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
data object Splash : AppDestination

@Serializable
data object UserForm : AppDestination

@Serializable
data object UserInfo : AppDestination

@Serializable
data object UserEdit : AppDestination