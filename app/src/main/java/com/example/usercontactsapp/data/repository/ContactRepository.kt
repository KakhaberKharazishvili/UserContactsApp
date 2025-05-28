package com.example.usercontactsapp.data.repository

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getAllContacts(): Flow<List<ContactUiModel>>
    fun getContactsByCategory(category: ContactCategory): Flow<List<ContactUiModel>>
    fun searchContacts(query: String): Flow<List<ContactUiModel>>
    suspend fun insertContact(contact: ContactUiModel)
    suspend fun deleteContact(contact: ContactUiModel)
    suspend fun getContactById(id: Int): ContactUiModel?
}
