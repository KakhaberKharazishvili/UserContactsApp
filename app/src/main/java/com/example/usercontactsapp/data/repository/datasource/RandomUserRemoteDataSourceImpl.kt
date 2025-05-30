package com.example.usercontactsapp.data.repository.datasource

import com.example.usercontactsapp.data.api.RandomUserApiService
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.mapper.toUiModel
import com.example.usercontactsapp.presentation.model.ContactUiModel

class RandomUserRemoteDataSourceImpl(
    private val api: RandomUserApiService
) : RandomUserRemoteDataSource {
    override suspend fun getRandomUsers(
        count: Int, category: ContactCategory
    ): List<ContactUiModel> {
        return api.getUsers(results = count).results.map { dto ->
            dto.toUiModel(category)
        }
    }
}
