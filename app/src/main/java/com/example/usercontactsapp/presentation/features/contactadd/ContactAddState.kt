package com.example.usercontactsapp.presentation.features.contactadd

import com.example.domain.model.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel

data class ContactAddState(
    val isLoading: Boolean = false,
    val contacts: List<ContactUiModel> = emptyList(),
    val selectedContact: ContactUiModel? = null,
    val selectedCategory: ContactCategory = ContactCategory.FRIENDS
)
