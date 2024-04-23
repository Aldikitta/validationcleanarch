package com.aldikitta.validationcleanarch

import com.aldikitta.validationcleanarch.model.ErrorResponseApi
import com.aldikitta.validationcleanarch.validator.UiText

data class FormState(
    val name: String = "",
    val nameErrorMessage: UiText? = null,
    val isNameSuccessInput: Boolean = false,
    val isEnableButtonSubmit: Boolean = false,
    val error: ErrorResponseApi.ErrorResponse? = null,
)