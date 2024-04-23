package com.aldikitta.validationcleanarch

import androidx.lifecycle.ViewModel
import com.aldikitta.validationcleanarch.validation.ValidationName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormViewModel(
    private val validationName: ValidationName = ValidationName()
) : ViewModel() {
    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.NameOnChanged -> {
                val nameResult = validationName.execute(event.name)
                nameResult.let { name ->
                    _state.update {
                        it.copy(
                            name = event.name,
                            nameErrorMessage = name.errorMessage,
                            isNameSuccessInput = name.isSuccessful
                        )
                    }
                }
            }

            is FormEvent.Submit -> {

            }
        }
    }
}