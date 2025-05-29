package com.example.usercontactsapp.presentation.features.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.usercontactsapp.R

@Composable
fun UserFormContent(
    modifier: Modifier = Modifier,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    birthDate: String,
    onBirthDateChange: (String) -> Unit,
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            label = { Text(stringResource(R.string.first_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            label = { Text(stringResource(R.string.last_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = { Text(stringResource(R.string.phone)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = birthDate,
            onValueChange = onBirthDateChange,
            label = { Text(stringResource(R.string.birth_date)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onPickImage, modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.choose_photo))
        }
        Button(
            onClick = onSave, modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save))
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
    }
}
