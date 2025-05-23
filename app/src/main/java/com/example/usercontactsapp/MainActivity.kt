package com.example.usercontactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.usercontactsapp.ui.navigation.UserContactsNavGraph
import com.example.usercontactsapp.ui.theme.UserContactsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            UserContactsAppTheme {
                val navController = rememberNavController()
                Surface {
                    UserContactsNavGraph(navController = navController)
                }
            }
        }
    }
}
