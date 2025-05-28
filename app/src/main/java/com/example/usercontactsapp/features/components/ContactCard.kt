package com.example.usercontactsapp.features.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.model.ContactUiModel

@Composable
fun ContactCard(
    contact: ContactUiModel, onClick: () -> Unit, onDelete: () -> Unit
) {
    Card(
        onClick = onClick, modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "${contact.firstName} ${contact.lastName}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(contact.email, style = MaterialTheme.typography.bodySmall)
            Text(
                "${stringResource(R.string.contact_category_label)}: ${contact.category.name}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.height(4.dp))
            TextButton(onClick = onDelete) {
                Text(stringResource(R.string.delete))
            }
        }
    }
}
