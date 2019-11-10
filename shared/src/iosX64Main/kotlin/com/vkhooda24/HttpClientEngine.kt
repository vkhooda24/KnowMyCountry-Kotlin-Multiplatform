package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

actual object HttpClientEngine {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create()  }

}