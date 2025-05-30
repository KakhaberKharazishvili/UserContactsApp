package com.example.data.repository.datasource

import com.example.data.local.dao.ContactDao
import com.example.data.repository.mapper.toDomain
import com.example.data.repository.mapper.toEntity
import com.example.domain.model.Contact
import com.example.domain.model.ContactCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalContactDataSourceImpl(
    private val contactDao: ContactDao
) : LocalContactDataSource {

    override fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts().map { it.map { it.toDomain() } }
    }

    override fun getContactsByCategory(category: ContactCategory): Flow<List<Contact>> {
        return contactDao.getContactsByCategory(category).map { it.map { it.toDomain() } }
    }

    override fun searchContacts(query: String): Flow<List<Contact>> {
        return contactDao.searchContacts(query).map { it.map { it.toDomain() } }
    }

    override suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact.toEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toEntity())
    }

    override suspend fun getContactById(id: Int): Contact? {
        return contactDao.getContactById(id)?.toDomain()
    }
}
