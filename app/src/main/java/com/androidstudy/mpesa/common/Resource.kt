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

    fun status(): Status {
        return status
    }

}
