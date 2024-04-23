package com.aldikitta.validationcleanarch

import androidx.lifecycle.ViewModel
import com.aldikitta.validationcleanarch.model.ErrorResponseApi
import com.aldikitta.validationcleanarch.validation.ValidationName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
                submitData()
            }
        }
        checkEnableButtonSubmit()
    }

    private fun checkEnableButtonSubmit() {
        val stateSuccess = _state.value
        val hasSuccess = listOf(
            stateSuccess.isNameSuccessInput,
        ).all { it }

        _state.update {
            it.copy(isEnableButtonSubmit = hasSuccess)
        }
    }

    private fun submitData() {
        val nameResult = validationName.execute(_state.value.name)
        val hasError = listOf(
            nameResult,
        ).any { it.isSuccessful.not() }

        if (hasError) {
            _state.update {
                it.copy(
                    nameErrorMessage = nameResult.errorMessage,
                )
            }

            return
        }

        handlePostSubmitForm()
    }

    private fun handlePostSubmitForm() {
        val error = _state.value.error?.errors?.associate {
            it?.field to it?.message
        }

        val nameResultApi = validationName.executeApi(errorMessage = error?.get("name"))

        val hasErrorApi = listOf(
            nameResultApi,
        ).any { !it.isSuccessful }

        if (hasErrorApi) {
            _state.update {
                it.copy(
                    nameErrorMessage = nameResultApi.errorMessage,
                )
            }

            return
        }
    }

    fun jsonMock(json: String) {
        val gson = Gson()
        val error = gson.fromJson(json, object : TypeToken<ErrorResponseApi.ErrorResponse>() {}.type) as ErrorResponseApi.ErrorResponse
        _state.update {
            it.copy(
                error = error
            )
        }
    }
}