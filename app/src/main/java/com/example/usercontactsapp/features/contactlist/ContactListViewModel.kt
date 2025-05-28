package com.example.usercontactsapp.features.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.model.ContactUiModel
import com.example.usercontactsapp.data.repository.ContactRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ContactListState())
    val state: StateFlow<ContactListState> = _state.asStateFlow()

    private val _onContactDeleted = MutableSharedFlow<Unit>()
    val onContactDeleted: SharedFlow<Unit> = _onContactDeleted.asSharedFlow()

    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllContacts().collect { list ->
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

        viewModelScope.launch(Dispatchers.IO) {
            if (query.isBlank()) {
                loadContacts()
            } else {
                repository.searchContacts(query).collect { list ->
                    _state.update { it.copy(contacts = list.toImmutableList()) }
                }
            }
        }
    }

    fun onCategoryChange(category: ContactCategory?) {
        _state.update { it.copy(selectedCategory = category) }

        viewModelScope.launch(Dispatchers.IO) {
            val flow = category?.let { repository.getContactsByCategory(it) }
                ?: repository.getAllContacts()

            flow.collect { list ->
                _state.update { it.copy(contacts = list.toImmutableList()) }
            }
        }
    }

    fun deleteContact(contact: ContactUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
            _onContactDeleted.emit(Unit)
            onCategoryChange(state.value.selectedCategory)
        }
    }
}
