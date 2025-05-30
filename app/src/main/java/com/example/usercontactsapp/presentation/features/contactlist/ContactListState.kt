package com.example.usercontactsapp.presentation.features.contactlist


import com.example.domain.model.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ContactListState(
    val isLoading: Boolean = true,
    val contacts: ImmutableList<ContactUiModel> = persistentListOf(),
    val selectedCategory: ContactCategory? = null,
    val searchQuery: String = ""
)
