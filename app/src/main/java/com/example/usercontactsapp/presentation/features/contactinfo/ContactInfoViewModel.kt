package com.example.usercontactsapp.presentation.features.contactinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.ContactRepository
import com.example.usercontactsapp.presentation.model.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactInfoViewModel(
    private val contactId: Int,
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ContactInfoState())
    val state: StateFlow<ContactInfoState> = _state.asStateFlow()

    init {
        loadContact()
    }

    private fun loadContact() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                contactRepository.getContactById(contactId)
            }.onSuccess { contact ->
                contact?.let {
                    _state.update { it.copy(contact = contact.toUiModel()) }
                }
            }
        }
    }
}