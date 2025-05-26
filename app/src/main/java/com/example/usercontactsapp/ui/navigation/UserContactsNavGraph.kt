package com.example.usercontactsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.usercontactsapp.features.navigation.*
import com.example.usercontactsapp.features.splash.SplashScreen
import com.example.usercontactsapp.features.useredit.UserEditScreen
import com.example.usercontactsapp.features.userform.UserFormScreen
import com.example.usercontactsapp.features.userinfo.UserInfoScreen

@Composable
fun UserContactsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(onUserAvailable = {
                navController.navigate(UserInfo) {
                    popUpTo(Splash) {
                        inclusive = true
                    }
                }
            }, onUserMissing = {
                navController.navigate(UserForm) {
                    popUpTo(Splash) {
                        inclusive = true
                    }
                }
            })
        }

        composable<UserForm> {
            UserFormScreen(
                onSave = {
                    navController.navigate(UserInfo) {
                        popUpTo(UserForm) {
                            inclusive = true
                        }
                    }
                })
        }

        composable<UserInfo> {
            UserInfoScreen(
                onEdit = {
                    navController.navigate(UserEdit)
                })
        }

        composable<UserEdit> {
            UserEditScreen(
                onSave = {
                    navController.navigate(UserInfo) {
                        popUpTo(UserEdit) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}
