package com.androidstudy.daraja.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("requestId")
        var requestId: String,
        @SerializedName("errorCode")
        var code: String,
        @SerializedName("errorMessage")
        var message: String
)