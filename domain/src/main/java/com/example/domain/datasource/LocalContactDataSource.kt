package com.example.domain.datasource

import com.example.domain.model.Contact
import com.example.domain.model.ContactCategory
import kotlinx.coroutines.flow.Flow

interface LocalContactDataSource {
    fun getAllContacts(): Flow<List<Contact>>
    fun getContactsByCategory(category: ContactCategory): Flow<List<Contact>>
    fun searchContacts(query: String): Flow<List<Contact>>
    suspend fun insertContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun getContactById(id: Int): Contact?
}
