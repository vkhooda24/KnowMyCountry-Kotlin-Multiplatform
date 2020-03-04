package com.vkhooda24

import com.vkhooda24.service.CountryDetailsResponseListener
import com.vkhooda24.service.CountryService
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2020-03-01.
 */
class CountryDetailsPresenter(
    context: CoroutineContext,
    private val countryDetailsResponseListener: CountryDetailsResponseListener
) : CoroutineScopePresenter(context, countryDetailsResponseListener) {

    fun fetchCountryDetails(countryName: String) = launch {
        countryDetailsResponseListener.countryDetailResponse(
            CountryService(HttpClientEngine.httpClientEngine).getCountryDetail(countryName)[0]
        )
    }
}