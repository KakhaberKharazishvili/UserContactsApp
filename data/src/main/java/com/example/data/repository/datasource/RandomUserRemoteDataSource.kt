package com.example.data.repository.datasource

import com.example.domain.model.ContactCategory
import com.example.domain.model.User

interface RandomUserRemoteDataSource {
    suspend fun getRandomUsers(count: Int, category: ContactCategory): List<User>
}
