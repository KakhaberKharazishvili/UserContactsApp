package com.example.usercontactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.usercontactsapp.ui.navigation.UserContactsNavGraph
import com.example.usercontactsapp.ui.theme.UserContactsAppTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.usercontactsapp.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            UserContactsAppTheme {
                val navController = rememberNavController()
                Surface {
                    UserContactsNavGraph(
                        navController = navController,
                        startDestination = splashViewModel.startDestination
                    )
                }
            }
        }
    }
}
