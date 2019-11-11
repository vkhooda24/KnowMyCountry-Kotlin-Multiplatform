package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios
import io.ktor.client.request.request

actual object HttpClientEngine {

    actual val httpClientEngine: HttpClientEngine by lazy {
        Ios.create {

            request {
                url
                print("iOSRequest: url: $this , body: $body")
            }

            print("iOSResponse: $response")
        }
    }

}