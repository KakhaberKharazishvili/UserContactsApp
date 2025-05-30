package com.example.usercontactsapp.data.repository.datasource

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel

interface RandomUserRemoteDataSource {
    suspend fun getRandomUsers(count: Int, category: ContactCategory): List<ContactUiModel>
}
