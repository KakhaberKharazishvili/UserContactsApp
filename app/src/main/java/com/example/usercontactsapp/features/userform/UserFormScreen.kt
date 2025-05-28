package com.example.usercontactsapp.features.userform

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.usercontactsapp.R
import com.example.usercontactsapp.features.components.UserFormContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    onSave: () -> Unit, viewModel: UserFormViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.onImageChange(it) }
        }

    LaunchedEffect(Unit) {
        viewModel.onSaveSuccess.collect {
            onSave()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.form_title)) })
        }) { padding ->

        UserFormContent(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            firstName = state.firstName,
            onFirstNameChange = viewModel::onFirstNameChange,
            lastName = state.lastName,
            onLastNameChange = viewModel::onLastNameChange,
            phone = state.phone,
            onPhoneChange = viewModel::onPhoneChange,
            email = state.email,
            onEmailChange = viewModel::onEmailChange,
            birthDate = state.birthDate,
            onBirthDateChange = viewModel::onBirthDateChange,
            imageUri = state.imageUri,
            onPickImage = { imagePickerLauncher.launch("image/*") },
            onSave = { viewModel.onSaveClicked() })
    }
}
