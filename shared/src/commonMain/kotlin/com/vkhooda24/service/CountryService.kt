package com.vkhooda24.service

import com.vkhooda24.knowyourcountry.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

/**
 * Created by Vikram Hooda on 2019-11-09.
 */
class CountryService(httpClientEngine: HttpClientEngine) {

    companion object {
        const val HOST_URL = "restcountries.eu/rest/v2"
        const val REGION_ALL = "all"
    }

    //create http client engine
    private val httpClient = HttpClient(httpClientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getCountriesList(regionName: String = "all"): List<Country> {

        val response = httpClient.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST_URL

                encodedPath = when (regionName.toLowerCase()) {
                    REGION_ALL -> "all"
                    else -> "region/$regionName"
                }
            }
        }

        val jsonBody = response.readText()
        return Json.nonstrict.parse(Country.serializer().list, jsonBody)
    }

    suspend fun getCountryDetail(countryName: String = "United States"): List<Country> {

        val response = httpClient.get<HttpResponse> {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST_URL
                encodedPath = "name/$countryName?fullText=true"
            }
        }

        val jsonBody = response.readText()
        return Json.nonstrict.parse(Country.serializer().list, jsonBody)
    }
}