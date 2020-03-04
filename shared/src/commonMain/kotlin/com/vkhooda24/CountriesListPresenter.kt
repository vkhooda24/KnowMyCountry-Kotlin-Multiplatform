package com.vkhooda24

import com.vkhooda24.service.CountriesListResponseListener
import com.vkhooda24.service.CountryService
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2020-03-01.
 */
class CountriesListPresenter(
    context: CoroutineContext,
    private val countriesListResponseListener: CountriesListResponseListener
) : CoroutineScopePresenter(context, countriesListResponseListener) {
    // Fetch countries list
    fun fetchCountiesList(regionName: String) = launch {

        val countriesList =
            CountryService(HttpClientEngine.httpClientEngine).getCountriesList(regionName)

        val sortedByDescending = countriesList.sortedByDescending { country -> country.name }

        countriesListResponseListener.countriesListResponse(sortedByDescending)
    }
}
//val sortedByDescending = countriesList.sortedByDescending { country -> country.name }
