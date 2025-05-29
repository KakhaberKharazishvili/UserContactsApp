package com.example.usercontactsapp.presentation.features.contactinfo

import com.example.usercontactsapp.data.local.ContactCategory
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.usercontactsapp.R
import com.example.usercontactsapp.presentation.features.components.ContactDetailCard
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfoScreen(
    contactId: Int
) {
    val viewModel: ContactInfoViewModel = koinViewModel(parameters = {
        parametersOf(contactId)
    })

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.contact_info_title)) })
        }) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            state.contact?.let { contact ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ContactDetailCard(contact = contact)

                    Text(
                        text = stringResource(
                            when (contact.category) {
                                ContactCategory.FAMILY -> R.string.category_family
                                ContactCategory.FRIENDS -> R.string.category_friends
                                ContactCategory.WORK -> R.string.category_work
                            }
                        ), style = MaterialTheme.typography.bodyLarge
                    )
                }
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
