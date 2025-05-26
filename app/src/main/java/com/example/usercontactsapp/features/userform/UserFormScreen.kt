package com.example.usercontactsapp.features.userform

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.model.UserUiModel
import com.example.usercontactsapp.features.sharedform.UserFormContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    onSave: () -> Unit, viewModel: UserFormViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { imageUri = it }
        }

    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.form_title)) })
    }) { padding ->

        UserFormContent(
            modifier = Modifier.padding(padding),
            firstName = firstName,
            onFirstNameChange = { firstName = it },
            lastName = lastName,
            onLastNameChange = { lastName = it },
            phone = phone,
            onPhoneChange = { phone = it },
            email = email,
            onEmailChange = { email = it },
            birthDate = birthDate,
            onBirthDateChange = { birthDate = it },
            imageUri = imageUri,
            onPickImage = { imagePickerLauncher.launch("image/*") },
            onSave = {
                if (firstName.isNotBlank() && lastName.isNotBlank()) {
                    val user = UserUiModel(
                        id = 0,
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        email = email,
                        birthDate = birthDate,
                        imageUri = imageUri.toString()
                    )
                    coroutineScope.launch {
                        viewModel.insertUser(user)
                        onSave()
                    }
                }
            })
    }
}
