package com.example.usercontactsapp.features.contactadd

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.usercontactsapp.R
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.features.components.ContactCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactAddScreen(
    onBack: () -> Unit, viewModel: ContactAddViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onSaved.collect {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_contact_title)) })
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = viewModel::loadRandomUser, modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.get_random_user))
            }

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.contact?.let { contact ->
                ContactCard(contact = contact, onClick = {}, onDelete = {})
            }

            Text(
                text = stringResource(R.string.select_category),
                style = MaterialTheme.typography.titleMedium
            )

            ContactCategory.entries.forEach { category ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
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

            Button(
                onClick = viewModel::saveContact,
                enabled = state.contact != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save_contact))
            }
        }
    }
}
