package com.vkhooda24.service

import com.vkhooda24.knowyourcountry.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

/**
 * Created by Vikram Hooda on 2019-11-09.
 */
internal const val HOST_URL = "restcountries.eu/rest/v2"
internal const val REGION = "region"
internal const val REGION_ALL = "all"

@UseExperimental(kotlinx.serialization.UnstableDefault::class)
class CountryService(httpClientEngine: HttpClientEngine?) {

    private var httpClient: HttpClient? = null
    init {
        httpClient = httpClientEngine?.let {
            HttpClient(it)
        } ?: HttpClient()
    }

    suspend fun getCountriesList(regionName: String = REGION_ALL): List<Country> {
        val response = httpClient?.get<HttpResponse> {
            val region = when (regionName.toLowerCase()) {
                REGION_ALL -> REGION_ALL
                else -> "$REGION/$regionName"
            }
            apiUrl(region)
        }

        return response?.readText()?.let {
            Json.nonstrict.parse(Country.serializer().list, it)
        } ?: emptyList()
    }

    suspend fun getCountryDetail(countryName: String = ""): List<Country> {
        val response = httpClient?.get<HttpResponse> {
            apiUrl("name/$countryName?fullText=true")
        }
        return response?.readText()?.let {
            return Json.nonstrict.parse(Country.serializer().list, it)
        } ?: emptyList()
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            protocol = URLProtocol.HTTPS
            host = HOST_URL
            encodedPath = path
        }
    }
}