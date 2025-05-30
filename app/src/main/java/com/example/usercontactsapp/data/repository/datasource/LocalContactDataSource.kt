package com.example.usercontactsapp.data.repository.datasource

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.local.ContactEntity
import kotlinx.coroutines.flow.Flow

interface LocalContactDataSource {
    fun getAllContacts(): Flow<List<ContactEntity>>
    fun getContactsByCategory(category: ContactCategory): Flow<List<ContactEntity>>
    fun searchContacts(query: String): Flow<List<ContactEntity>>
    suspend fun insertContact(contact: ContactEntity)
    suspend fun deleteContact(contact: ContactEntity)
    suspend fun getContactById(id: Int): ContactEntity?
}
