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

import androidx.lifecycle.LiveData
import com.androidstudy.daraja.callback.DarajaException
import com.androidstudy.daraja.callback.DarajaListener

class DarajaLiveData<T> : LiveData<Resource<T>>(), DarajaListener<T> {

    init {
        value = Resource(Status.LOADING)
    }

    override fun onResult(result: T) {
        value = Resource(result)
    }

    override fun onError(exception: DarajaException) {
        value = Resource(DarajaException(exception.localizedMessage))
    }
}