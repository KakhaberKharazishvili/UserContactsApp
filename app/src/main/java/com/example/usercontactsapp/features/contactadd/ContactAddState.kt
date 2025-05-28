package com.example.usercontactsapp.features.contactadd

import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel

data class ContactAddState(
    val isLoading: Boolean = false,
    val contact: ContactUiModel? = null,
    val selectedCategory: ContactCategory = ContactCategory.FRIENDS
)
