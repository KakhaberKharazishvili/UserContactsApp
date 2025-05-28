package com.example.usercontactsapp.features.contactlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.model.ContactUiModel
import com.example.usercontactsapp.features.components.ContactCard
import com.example.usercontactsapp.features.components.SearchAndFilterSection
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    onAddClick: () -> Unit,
    onContactClick: (ContactUiModel) -> Unit,
    viewModel: ContactListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onContactDeleted.collect {}
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.contacts_title)) })
    }, floatingActionButton = {
        FloatingActionButton(onClick = onAddClick) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_contact))
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SearchAndFilterSection(
                searchQuery = state.searchQuery,
                onSearchChange = viewModel::onSearchQueryChange,
                selectedCategory = state.selectedCategory,
                onCategoryChange = viewModel::onCategoryChange
            )

            if (state.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(state.contacts) { contact ->
                        ContactCard(
                            contact = contact,
                            onClick = { onContactClick(contact) },
                            onDelete = { viewModel.deleteContact(contact) })
                    }
                }
            }
        }
    }
}
