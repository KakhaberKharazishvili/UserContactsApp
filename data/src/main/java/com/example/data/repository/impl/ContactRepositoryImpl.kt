package com.example.data.repository.impl

import com.example.domain.model.Contact
import com.example.domain.model.ContactCategory
import com.example.domain.repository.ContactRepository
import com.example.domain.datasource.LocalContactDataSource
import kotlinx.coroutines.flow.Flow

internal class ContactRepositoryImpl(
    private val localDataSource: LocalContactDataSource
) : ContactRepository {

    override fun getAllContacts(): Flow<List<Contact>> {
        return localDataSource.getAllContacts()
    }

    override fun getContactsByCategory(category: ContactCategory): Flow<List<Contact>> {
        return localDataSource.getContactsByCategory(category)
    }

    override fun searchContacts(query: String): Flow<List<Contact>> {
        return localDataSource.searchContacts(query)
    }

    override suspend fun insertContact(contact: Contact) {
        localDataSource.insertContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        localDataSource.deleteContact(contact)
    }

    override suspend fun getContactById(id: Int): Contact? {
        return localDataSource.getContactById(id)
    }
}
