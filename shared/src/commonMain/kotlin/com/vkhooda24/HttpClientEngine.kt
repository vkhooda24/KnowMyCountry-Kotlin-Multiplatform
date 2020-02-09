package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine
import kotlin.native.concurrent.ThreadLocal

expect object HttpClientEngine {

    @ThreadLocal
    val httpClientEngine: HttpClientEngine
}