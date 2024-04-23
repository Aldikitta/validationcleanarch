package com.aldikitta.validationcleanarch.validator

abstract class Validation<T> {

    abstract fun execute(input: T): ValidationResult?

    fun executeApi(errorMessage: String?): ValidationResult {
        return if (errorMessage.isNullOrEmpty()) {
            ValidationResult(
                isSuccessful = true,
                errorMessage = null,
            )
        } else {
            ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.DynamicString(errorMessage),
            )
        }
    }
}