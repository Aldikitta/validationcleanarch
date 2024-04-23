package com.aldikitta.validationcleanarch.model

import com.google.gson.annotations.SerializedName

sealed interface ErrorResponseApi {
    data class ErrorResponse(

        @field:SerializedName("code")
        val code: Int? = null,

        @field:SerializedName("data")
        val data: Any? = null,

        @field:SerializedName("message")
        val message: String? = null,

        @field:SerializedName("request_id")
        val requestId: String? = null,

        @field:SerializedName("errors")
        val errors: List<ErrorsItem?>? = null,

        @field:SerializedName("timestamp")
        val timestamp: String? = null
    )

    data class ErrorsItem(

        @field:SerializedName("field")
        val field: String? = null,

        @field:SerializedName("message")
        val message: String? = null
    )
}