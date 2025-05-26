package com.example.usercontactsapp.features.useredit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.usercontactsapp.R
import com.example.usercontactsapp.features.sharedform.UserFormContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEditScreen(
    onSave: () -> Unit, viewModel: UserEditViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri -> uri?.let { viewModel.onImageChange(it) } }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.edit)) }) }) { padding ->

        UserFormContent(
            modifier = Modifier.padding(padding),
            firstName = viewModel.firstName,
            onFirstNameChange = viewModel::onFirstNameChange,
            lastName = viewModel.lastName,
            onLastNameChange = viewModel::onLastNameChange,
            phone = viewModel.phone,
            onPhoneChange = viewModel::onPhoneChange,
            email = viewModel.email,
            onEmailChange = viewModel::onEmailChange,
            birthDate = viewModel.birthDate,
            onBirthDateChange = viewModel::onBirthDateChange,
            imageUri = viewModel.imageUri,
            onPickImage = { imagePickerLauncher.launch("image/*") },
            onSave = {
                scope.launch {
                    viewModel.save()
                    onSave()
                }
            })
    }
}
