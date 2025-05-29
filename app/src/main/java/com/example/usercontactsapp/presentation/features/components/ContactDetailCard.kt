package com.example.usercontactsapp.presentation.features.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.usercontactsapp.R
import com.example.usercontactsapp.presentation.model.ContactUiModel

@Composable
fun ContactDetailCard(
    contact: ContactUiModel, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (contact.imageUri.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(contact.imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "${contact.firstName} ${contact.lastName}",
                style = MaterialTheme.typography.titleMedium
            )

            Text("${stringResource(R.string.phone)}: ${contact.phone}")
            Text("${stringResource(R.string.email)}: ${contact.email}")
            Text("${stringResource(R.string.birth_date)}: ${contact.birthDate}")
            Text(
                text = stringResource(R.string.category_format, contact.category.name),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
