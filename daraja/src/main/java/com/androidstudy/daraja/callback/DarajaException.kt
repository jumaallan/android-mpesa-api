package com.androidstudy.daraja.callback

import com.androidstudy.daraja.model.ErrorResponse

class DarajaException : Exception {

    lateinit var errorResponse: ErrorResponse

    constructor(message: String) : super(message)

    constructor(errorResponse: ErrorResponse) : super("${errorResponse.code} : ${errorResponse.message}") {
        this.errorResponse = errorResponse
    }

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)


}