package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual object HttpClientEngine {
    actual val httpClientEngine: HttpClientEngine by lazy {
        Android.create {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }
}