package com.vkhooda24

import com.vkhooda24.service.CountryService
import com.vkhooda24.service.UiUpdate
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
class CountryPresenter(
    context: CoroutineContext,
    val uiUpdate: UiUpdate
) : CoroutineScopePresenter(context, uiUpdate) {

    // Fetch countries list
    fun getCountryList(regionName: String) = launch {
        uiUpdate.countryListResponse(
            CountryService(HttpClientEngine.httpClientEngine).getCountriesList(
                regionName
            )
        )
    }

    //
//    //Fetch country detail
    fun getCountryDetail(countryName: String) = launch {
        uiUpdate.countryDetailResponse(
            CountryService(HttpClientEngine.httpClientEngine).getCountryDetail(
                countryName
            ).get(0)
        )
    }
}