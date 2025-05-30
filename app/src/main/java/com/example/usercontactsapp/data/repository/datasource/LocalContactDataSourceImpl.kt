package com.example.usercontactsapp.data.repository.datasource

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.local.ContactDao
import com.example.usercontactsapp.data.local.ContactEntity
import kotlinx.coroutines.flow.Flow

class LocalContactDataSourceImpl(
    private val contactDao: ContactDao
) : LocalContactDataSource {

    override fun getAllContacts(): Flow<List<ContactEntity>> {
        return contactDao.getAllContacts()
    }

    override fun getContactsByCategory(category: ContactCategory): Flow<List<ContactEntity>> {
        return contactDao.getContactsByCategory(category)
    }

    override fun searchContacts(query: String): Flow<List<ContactEntity>> {
        return contactDao.searchContacts(query)
    }

    override suspend fun insertContact(contact: ContactEntity) {
        contactDao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity) {
        contactDao.deleteContact(contact)
    }

    override suspend fun getContactById(id: Int): ContactEntity? {
        return contactDao.getContactById(id)
    }
}
