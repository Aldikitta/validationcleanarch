package com.aldikitta.validationcleanarch.validator

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMessage: UiText? = null,
)