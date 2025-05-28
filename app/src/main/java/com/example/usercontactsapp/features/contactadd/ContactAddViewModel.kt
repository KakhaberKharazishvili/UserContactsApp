package com.example.usercontactsapp.features.contactadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usercontactsapp.data.local.ContactCategory
import com.example.usercontactsapp.data.repository.ContactRepository
import com.example.usercontactsapp.data.repository.datasource.RandomUserRemoteDataSource
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

    fun onCategoryChange(category: ContactCategory) {
        _state.update { it.copy(selectedCategory = category) }
    }

    fun loadRandomUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            runCatching {
                randomUserDataSource.getRandomUser(state.value.selectedCategory)
            }.onSuccess { contact ->
                _state.update {
                    it.copy(
                        contact = contact, isLoading = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    fun saveContact() {
        state.value.contact?.let { contact ->
            viewModelScope.launch(Dispatchers.IO) {
                contactRepository.insertContact(contact)
                _onSaved.emit(Unit)
            }
        }
    }
}
