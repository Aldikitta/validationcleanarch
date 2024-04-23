package com.aldikitta.validationcleanarch

import com.aldikitta.validationcleanarch.validator.UiText

data class FormState(
    val name: String = "",
    val nameErrorMessage: UiText? = null,
    val isNameSuccessInput: Boolean = false
)