package com.example.usercontactsapp.features.contactlist

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ContactListState(
    val isLoading: Boolean = true,
    val contacts: ImmutableList<ContactUiModel> = persistentListOf(),
    val selectedCategory: ContactCategory? = null,
    val searchQuery: String = ""
)
