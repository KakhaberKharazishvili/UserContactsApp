package com.example.usercontactsapp.features.userform

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.usercontactsapp.R
import com.example.usercontactsapp.features.sharedform.UserFormContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    onSave: () -> Unit, viewModel: UserFormViewModel = koinViewModel()
) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val email by viewModel.email.collectAsState()
    val birthDate by viewModel.birthDate.collectAsState()
    val imageUri by viewModel.imageUri.collectAsState()

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { viewModel.onImageChange(it) }
        }

    LaunchedEffect(Unit) {
        viewModel.onSaveSuccess.collect {
            onSave()
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.form_title)) })
    }) { padding ->

        UserFormContent(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            firstName = firstName,
            onFirstNameChange = viewModel::onFirstNameChange,
            lastName = lastName,
            onLastNameChange = viewModel::onLastNameChange,
            phone = phone,
            onPhoneChange = viewModel::onPhoneChange,
            email = email,
            onEmailChange = viewModel::onEmailChange,
            birthDate = birthDate,
            onBirthDateChange = viewModel::onBirthDateChange,
            imageUri = imageUri,
            onPickImage = { imagePickerLauncher.launch("image/*") },
            onSave = {
                viewModel.onSaveClicked()
            })
    }
}