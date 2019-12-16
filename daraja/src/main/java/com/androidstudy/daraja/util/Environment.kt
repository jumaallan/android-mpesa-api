package com.androidstudy.daraja.util

import com.androidstudy.daraja.network.URLs

enum class Environment(val url: String) {
    SANDBOX(URLs.SANDBOX_BASE_URL),
    PRODUCTION(URLs.PRODUCTION_BASE_URL)
}