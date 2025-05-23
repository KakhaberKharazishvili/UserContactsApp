package com.example.usercontactsapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.usercontactsapp.data.navigation.UserForm
import com.example.usercontactsapp.data.navigation.UserInfo
import com.example.usercontactsapp.ui.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
import android.util.Log


@Composable
fun SplashScreen(
    navController: NavController, viewModel: UserViewModel = koinViewModel()
) {
    val isLoaded = viewModel.isLoaded
    val user by viewModel.user.collectAsState()
    var isNavigated by remember { mutableStateOf(false) }

    LaunchedEffect(isLoaded) {
        Log.d("Splash", "user = $user, isLoaded = ${viewModel.isLoaded}")
        if (!isNavigated && viewModel.isLoaded) {
            isNavigated = true
            navController.popBackStack()
            if (user != null) {
                navController.navigate(UserInfo)
            } else {
                navController.navigate(UserForm)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


