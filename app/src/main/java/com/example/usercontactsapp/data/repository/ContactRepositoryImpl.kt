package com.example.usercontactsapp.data.repository

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel
import com.example.usercontactsapp.data.mapper.toEntity
import com.example.usercontactsapp.data.mapper.toUiModel
import com.example.usercontactsapp.data.repository.datasource.LocalContactDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactRepositoryImpl(
    private val localDataSource: LocalContactDataSource
) : ContactRepository {

    override fun getAllContacts(): Flow<List<ContactUiModel>> {
        return localDataSource.getAllContacts().map { list -> list.map { it.toUiModel() } }
    }

    override fun getContactsByCategory(category: ContactCategory): Flow<List<ContactUiModel>> {
        return localDataSource.getContactsByCategory(category)
            .map { list -> list.map { it.toUiModel() } }
    }

    override fun searchContacts(query: String): Flow<List<ContactUiModel>> {
        return localDataSource.searchContacts(query).map { list -> list.map { it.toUiModel() } }
    }

    override suspend fun insertContact(contact: ContactUiModel) {
        localDataSource.insertContact(contact.toEntity())
    }

    override suspend fun deleteContact(contact: ContactUiModel) {
        localDataSource.deleteContact(contact.toEntity())
    }

    override suspend fun getContactById(id: Int): ContactUiModel? {
        return localDataSource.getContactById(id)?.toUiModel()
    }
}
