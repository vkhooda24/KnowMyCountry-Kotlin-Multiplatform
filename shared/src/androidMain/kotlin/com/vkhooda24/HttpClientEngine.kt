package com.vkhooda24

import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.service.CountryService
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.runBlocking
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