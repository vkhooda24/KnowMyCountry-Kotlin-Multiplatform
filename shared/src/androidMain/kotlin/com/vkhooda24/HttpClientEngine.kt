package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

actual object HttpClientEngine {

    actual val httpClientEngine: HttpClientEngine by lazy {
        OkHttp.create {
            val networkInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addNetworkInterceptor(networkInterceptor)
        }
    }
}