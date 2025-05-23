package com.example.usercontactsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.usercontactsapp.data.navigation.Splash
import com.example.usercontactsapp.data.navigation.UserEdit
import com.example.usercontactsapp.data.navigation.UserForm
import com.example.usercontactsapp.data.navigation.UserInfo
import com.example.usercontactsapp.ui.screen.SplashScreen
import com.example.usercontactsapp.ui.screen.UserEditScreen
import com.example.usercontactsapp.ui.screen.UserFormScreen
import com.example.usercontactsapp.ui.screen.UserInfoScreen

@Composable
fun UserContactsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(navController)
        }

        composable<UserForm> {
            UserFormScreen(navController)
        }

        composable<UserInfo> {
            UserInfoScreen(navController)
        }
        composable<UserEdit> {
            UserEditScreen(navController)
        }

    }
}
