package com.example.usercontactsapp.features.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.usercontactsapp.R
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
    onEdit: () -> Unit, viewModel: UserInfoViewModel = koinViewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.profile)) })
    }, floatingActionButton = {
        FloatingActionButton(onClick = onEdit) {
            Icon(
                imageVector = Icons.Default.Edit, contentDescription = stringResource(R.string.edit)
            )
        }
    }) { padding ->
        user?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (it.imageUri.isNotBlank()) {
                    Image(
                        painter = rememberAsyncImagePainter(it.imageUri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Text("${stringResource(R.string.first_name)}: ${it.firstName}")
                Text("${stringResource(R.string.last_name)}: ${it.lastName}")
                Text("${stringResource(R.string.phone)}: ${it.phone}")
                Text("${stringResource(R.string.email)}: ${it.email}")
                Text("${stringResource(R.string.birth_date)}: ${it.birthDate}")
            }
        } ?: Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
