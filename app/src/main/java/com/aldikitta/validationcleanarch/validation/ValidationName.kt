package com.aldikitta.validationcleanarch.validation

import com.aldikitta.validationcleanarch.R
import com.aldikitta.validationcleanarch.validator.UiText
import com.aldikitta.validationcleanarch.validator.Validation
import com.aldikitta.validationcleanarch.validator.ValidationResult


class ValidationName : Validation<String>() {
    override fun execute(input: String): ValidationResult {
        val specialChar = Regex("[^.,\\s\\p{L}\\p{N}]")
        return when {
            input.isEmpty() -> ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(R.string.name_empty),
            )

            input.isBlank() -> ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(R.string.name_space_only),
            )

            input.length < 3 -> ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(R.string.name_min_3),
            )

            input.length > 50 -> ValidationResult(
                isSuccessful = false,
                errorMessage = UiText.StringResource(R.string.name_max_50),
            )

//            specialChar.find(input) != null -> ValidationResult(
//                isSuccessful = false,
//                errorMessage = UiText.StringResource(R.string.error_nik_name_special_char),
//            )

            else -> ValidationResult(
                isSuccessful = true,
                errorMessage = null,
            )
        }
    }
}