package com.example.usercontactsapp.data.repository.datasource

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel

interface RandomUserRemoteDataSource {
    suspend fun getRandomUser(category: ContactCategory): ContactUiModel
}
