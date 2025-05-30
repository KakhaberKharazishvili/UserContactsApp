package com.example.data.repository.datasource

import com.example.data.repository.mapper.toDomain
import com.example.data.remote.api.RandomUserApiService
import com.example.domain.datasource.RandomUserRemoteDataSource
import com.example.domain.model.ContactCategory
import com.example.domain.model.User

internal class RandomUserRemoteDataSourceImpl(
    private val api: RandomUserApiService
) : RandomUserRemoteDataSource {

    override suspend fun getRandomUsers(count: Int, category: ContactCategory): List<User> {
        return api.getUsers(results = count).results.map { it.toDomain() }
    }
}
