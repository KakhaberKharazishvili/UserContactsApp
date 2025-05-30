package com.example.usercontactsapp.presentation.features.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.presentation.model.ContactUiModel
import com.example.usercontactsapp.data.repository.ContactRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ContactListState())
    val state: StateFlow<ContactListState> = _state.asStateFlow()

    private var subscriptionJob: Job? = null

    init {
        subscribeToContacts()
    }

    private fun subscribeToContacts(category: ContactCategory? = null, query: String = "") {
        subscriptionJob?.cancel()
        subscriptionJob = viewModelScope.launch(Dispatchers.IO) {
            val flow = when {
                query.isNotBlank() -> repository.searchContacts(query)
                category != null -> repository.getContactsByCategory(category)
                else -> repository.getAllContacts()
            }

            flow.collect { list ->
                _state.update {
                    it.copy(
                        isLoading = false, contacts = list.toImmutableList()
                    )
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _state.update { it.copy(searchQuery = query) }
        subscribeToContacts(state.value.selectedCategory, query)
    }

    fun onCategoryChange(category: ContactCategory?) {
        _state.update { it.copy(selectedCategory = category) }
        subscribeToContacts(category, state.value.searchQuery)
    }

    fun deleteContact(contact: ContactUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }
}