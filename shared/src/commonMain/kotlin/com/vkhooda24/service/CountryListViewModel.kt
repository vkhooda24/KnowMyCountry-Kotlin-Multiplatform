package com.vkhooda24.service

import com.vkhooda24.HttpClientEngine
import com.vkhooda24.domain.defaultDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-09.
 */

class CountryListViewModel {

    //Dispatcher
    private val coroutineContext: CoroutineContext = defaultDispatcher

    //Service call
    private val service by lazy {
        CountryService(HttpClientEngine.httpClientEngine)
    }

    //Coroutine Scope
    private val scope: CoroutineScope by lazy {
        CoroutineScope(coroutineContext)
    }

    // Fetch countries list
    suspend fun getCountryList(regionName: String) = service.getCountriesList(regionName)

    //Fetch country detail
    suspend fun getCountryDetail(countryName: String) = service.getCountryDetail(countryName).get(0)
}