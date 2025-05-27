package com.example.usercontactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.usercontactsapp.features.splash.SplashViewModel
import com.example.usercontactsapp.ui.navigation.UserContactsNavGraph
import com.example.usercontactsapp.ui.theme.UserContactsAppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        var showSplash = true
        installSplashScreen().setKeepOnScreenCondition { showSplash }

        super.onCreate(savedInstanceState)

        splashViewModel.checkStartDestination()

        lifecycleScope.launch {
            splashViewModel.startDestination.collect { destination ->
                showSplash = false

                setContent {
                    val navController = rememberNavController()

                    UserContactsAppTheme {
                        Surface {
                            UserContactsNavGraph(
                                navController = navController, startDestination = destination
                            )
                        }
                    }
                }
            }
        }
    }
}
