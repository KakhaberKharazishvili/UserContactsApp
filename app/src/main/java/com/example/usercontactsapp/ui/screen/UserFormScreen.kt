package com.example.usercontactsapp.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.model.User
import com.example.usercontactsapp.data.navigation.UserForm
import com.example.usercontactsapp.data.navigation.UserInfo
import com.example.usercontactsapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFormScreen(
    navController: NavController, viewModel: UserViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text((stringResource(R.string.form_title))) })
    }, content = { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(stringResource(R.string.first_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(stringResource(R.string.last_name)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(R.string.phone)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text(stringResource(R.string.birth_date)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.choose_photo))
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Button(
                onClick = {
                    if (firstName.isNotBlank() && lastName.isNotBlank()) {
                        val user = User(
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
                            navController.navigate(UserInfo) {
                                popUpTo(UserForm) { inclusive = true }
                            }
                        }
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save))
            }
        }
    })
}
