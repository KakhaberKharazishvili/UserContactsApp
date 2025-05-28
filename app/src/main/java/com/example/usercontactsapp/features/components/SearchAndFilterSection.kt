package com.example.usercontactsapp.features.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.local.ContactCategory

@Composable
fun SearchAndFilterSection(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedCategory: ContactCategory?,
    onCategoryChange: (ContactCategory?) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            label = { Text(stringResource(R.string.search_placeholder)) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChipItem(
                text = stringResource(R.string.category_all),
                selected = selectedCategory == null,
                onClick = { onCategoryChange(null) })
            FilterChipItem(
                text = stringResource(R.string.category_family),
                selected = selectedCategory == ContactCategory.FAMILY,
                onClick = { onCategoryChange(ContactCategory.FAMILY) })
            FilterChipItem(
                text = stringResource(R.string.category_friends),
                selected = selectedCategory == ContactCategory.FRIENDS,
                onClick = { onCategoryChange(ContactCategory.FRIENDS) })
            FilterChipItem(
                text = stringResource(R.string.category_work),
                selected = selectedCategory == ContactCategory.WORK,
                onClick = { onCategoryChange(ContactCategory.WORK) })
        }
    }
}

@Composable
private fun FilterChipItem(text: String, selected: Boolean, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick, label = { Text(text) }, colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.surface
        )
    )
}
