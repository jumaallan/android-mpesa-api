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
package com.androidstudy.mpesa.common

class Resource<T> private constructor(private val data: T?, private val error: Exception?) {

    private var status = Status.LOADING

    constructor(data: T) : this(data, null)

    constructor(status: Status) : this(null, null) {
        this.status = status
    }

    constructor(exception: Exception) : this(null, exception) {
        this.status = Status.ERROR
    }

    init {

        status = when {
            error != null -> Status.ERROR
            data != null -> Status.SUCCESS
            else -> Status.LOADING
        }
    }

    fun data(): T? {
        if (error != null) {
            throw IllegalStateException("error is not null.")
        }
        return data
    }

    fun error(): Exception? {
        if (data != null) {
            throw IllegalStateException("data is not null.")
        }
        return error
    }

    fun status(): Status = status
}