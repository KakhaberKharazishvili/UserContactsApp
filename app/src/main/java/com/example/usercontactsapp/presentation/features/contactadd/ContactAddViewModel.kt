package com.example.usercontactsapp.presentation.features.contactadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.repository.ContactRepository
import com.example.usercontactsapp.data.repository.datasource.RandomUserRemoteDataSource
import com.example.usercontactsapp.presentation.model.ContactUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactAddViewModel(
    private val randomUserDataSource: RandomUserRemoteDataSource,
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ContactAddState())
    val state: StateFlow<ContactAddState> = _state.asStateFlow()

    private val _onSaved = MutableSharedFlow<Unit>()
    val onSaved: SharedFlow<Unit> = _onSaved.asSharedFlow()

    fun onSelectContact(contact: ContactUiModel) {
        val updated = contact.copy(category = state.value.selectedCategory)
        _state.update { it.copy(selectedContact = updated) }
    }


    fun onCategoryChange(category: ContactCategory) {
        val selected = state.value.selectedContact?.copy(category = category)

        _state.update {
            it.copy(
                selectedCategory = category, selectedContact = selected
            )
        }
    }

    fun loadRandomUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            runCatching {
                randomUserDataSource.getRandomUsers(5, state.value.selectedCategory)
            }.onSuccess { contacts ->
                _state.update {
                    it.copy(
                        contacts = contacts, selectedContact = null, isLoading = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun saveContact() {
        state.value.selectedContact?.let { contact ->
            viewModelScope.launch(Dispatchers.IO) {
                contactRepository.insertContact(contact)
                _onSaved.emit(Unit)
            }
        }
    }
}
