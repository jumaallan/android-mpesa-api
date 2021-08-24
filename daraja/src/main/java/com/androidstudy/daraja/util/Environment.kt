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
package com.androidstudy.daraja.util

/**
 * Helper Class for switching between [SANDBOX] and [PRODUCTION] environments.
 * Useful for production apps where you can set your environment depending on
 * BuildType.
 */
enum class Environment(val url: String) {

    SANDBOX("https://sandbox.safaricom.co.ke/"),
    PRODUCTION("https://api.safaricom.co.ke/")
}