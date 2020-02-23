package com.vkhooda24.service

import com.vkhooda24.knowyourcountry.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

/**
 * Created by Vikram Hooda on 2019-11-09.
 */
internal const val HOST_URL = "restcountries.eu/rest/v2"
internal const val REGION_ALL = "all"

class CountryService(val httpClientEngine: HttpClientEngine) {

    suspend fun getCountriesList(regionName: String = "all"): List<Country> {
        val response = HttpClient(httpClientEngine).get<HttpResponse> {
            val region = when (regionName.toLowerCase()) {
                REGION_ALL -> "all"
                else -> "region/$regionName"
            }
            apiUrl(region)
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(Country.serializer().list, jsonBody)
    }

    suspend fun getCountryDetail(countryName: String = "United States"): List<Country> {
        val response = HttpClient(httpClientEngine).get<HttpResponse> {
            apiUrl("name/$countryName?fullText=true")
        }
        val jsonBody = response.readText()
        return Json.nonstrict.parse(Country.serializer().list, jsonBody)
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            protocol = URLProtocol.HTTPS
            host = HOST_URL
            encodedPath = path
        }
    }
}