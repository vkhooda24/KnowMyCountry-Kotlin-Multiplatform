package com.vkhooda24

import io.ktor.client.engine.HttpClientEngine

expect object HttpClientEngine {

    val httpClientEngine: HttpClientEngine
}