/*
 * Copyright 2021 Juma Allan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androidstudy.daraja.callback

import com.androidstudy.daraja.data.model.ErrorResponse

/**
 * Handles exceptions and messages for the exceptions.
 */
class DarajaException : Exception {

    lateinit var errorResponse: ErrorResponse

    constructor(message: String?) : super(message)

    constructor(errorResponse: ErrorResponse) : super("${errorResponse.code} : ${errorResponse.message}") {
        this.errorResponse = errorResponse
    }

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)
}