package com.example.usercontactsapp.presentation.features.contactadd

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.ContactCategory
import com.example.usercontactsapp.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactAddScreen(
    onBack: () -> Unit, viewModel: ContactAddViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadRandomUsers()
        viewModel.onSaved.collect {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_contact_title)) })
        }) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.isLoading) {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }

            items(state.contacts) { contact ->
                val isSelected = contact.email == state.selectedContact?.email

                ElevatedCard(
                    onClick = { viewModel.onSelectContact(contact) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (isSelected) Modifier.border(2.dp, MaterialTheme.colorScheme.primary)
                            else Modifier
                        )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${contact.firstName} ${contact.lastName}")
                        Text(contact.email, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            item {
                Text(
                    text = stringResource(R.string.select_category),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(ContactCategory.entries) { category ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = state.selectedCategory == category,
                        onClick = { viewModel.onCategoryChange(category) })
                    Text(
                        text = stringResource(
                            when (category) {
                                ContactCategory.FAMILY -> R.string.category_family
                                ContactCategory.FRIENDS -> R.string.category_friends
                                ContactCategory.WORK -> R.string.category_work
                            }
                        )
                    )
                }
            }

            item {
                Button(
                    onClick = viewModel::saveContact,
                    enabled = state.selectedContact != null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.save_contact))
                }
            }
        }
    }
}

