package com.example.usercontactsapp.features.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    onUserAvailable: () -> Unit,
    onUserMissing: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val userFlow = remember(viewModel, lifecycleOwner) {
        viewModel.user.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val user by userFlow.collectAsState(initial = null)
    val isLoaded = viewModel.isLoaded
    var hasNavigated by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isLoaded, user) {
        if (isLoaded && !hasNavigated) {
            hasNavigated = true
            if (user != null) {
                onUserAvailable()
            } else {
                onUserMissing()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
