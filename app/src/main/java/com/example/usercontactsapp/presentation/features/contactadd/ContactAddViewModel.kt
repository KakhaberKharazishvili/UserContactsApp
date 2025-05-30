package com.example.usercontactsapp.presentation.features.contactadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.datasource.RandomUserRemoteDataSource
import com.example.domain.model.ContactCategory
import com.example.domain.repository.ContactRepository
import com.example.usercontactsapp.presentation.model.ContactUiModel
import com.example.usercontactsapp.presentation.model.toContactUiModel
import com.example.usercontactsapp.presentation.model.toDomain
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
        _state.update { it.copy(selectedContact = contact) }
    }

    fun onCategoryChange(category: ContactCategory) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun loadRandomUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            runCatching {
                randomUserDataSource.getRandomUsers(5, state.value.selectedCategory)
            }.onSuccess { users ->
                val contactUiModels = users.map { it.toContactUiModel(state.value.selectedCategory) }
                _state.update {
                    it.copy(
                        contacts = contactUiModels,
                        selectedContact = null,
                        isLoading = false
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
                val contactWithCategory = contact.copy(category = state.value.selectedCategory)
                contactRepository.insertContact(contactWithCategory.toDomain())
                _onSaved.emit(Unit)
            }
        }
    }
}
