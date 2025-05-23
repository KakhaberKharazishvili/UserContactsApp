package com.example.usercontactsapp.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.usercontactsapp.data.navigation.UserEdit
import com.example.usercontactsapp.data.navigation.UserInfo
import com.example.usercontactsapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserEditScreen(
    navController: NavController, viewModel: UserViewModel = koinViewModel()
) {
    val userState by viewModel.user.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(userState) {
        userState?.let {
            firstName = it.firstName
            lastName = it.lastName
            phone = it.phone
            email = it.email
            birthDate = it.birthDate
            imageUri = Uri.parse(it.imageUri)
        }
    }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { imageUri = it }
        }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.edit)) }) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(stringResource(R.string.first_name)) })
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text(stringResource(R.string.last_name)) })
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(stringResource(R.string.phone)) })
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.email)) })
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = { Text(stringResource(R.string.birth_date)) })

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

                Button(onClick = {
                    userState?.let {
                        val updatedUser = it.copy(
                            firstName = firstName,
                            lastName = lastName,
                            phone = phone,
                            email = email,
                            birthDate = birthDate,
                            imageUri = imageUri.toString()
                        )
                        coroutineScope.launch {
                            viewModel.updateUser(updatedUser)
                            navController.navigate(UserInfo) {
                                popUpTo(UserEdit) { inclusive = true }
                            }
                        }
                    }
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.save))
                }
            }
        })
}
