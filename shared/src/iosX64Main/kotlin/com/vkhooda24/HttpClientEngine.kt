package com.vkhooda24

import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.service.CountryService
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios
import io.ktor.client.request.request
import kotlinx.coroutines.runBlocking

actual object HttpClientEngine {

    actual val httpClientEngine: HttpClientEngine by lazy {
        Ios.create {
            request {
                url
                print("iOSRequest: url: $url , body: $body")
            }
        }
    }

}