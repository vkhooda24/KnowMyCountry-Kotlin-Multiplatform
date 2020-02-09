package com.vkhooda24

import com.vkhooda24.service.CountryService
import com.vkhooda24.service.UICallback
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
class CountryPresenter(
    context: CoroutineContext,
    private val uiCallback: UICallback
) : CoroutineScopePresenter(context, uiCallback) {

    // Fetch countries list
    fun getCountryList(regionName: String) = launch {
        uiCallback.countryListResponse(
            CountryService().getCountriesList(regionName)
        )
    }

    //Fetch country detail
    fun getCountryDetail(countryName: String) = launch {
        uiCallback.countryDetailResponse(
            CountryService().getCountryDetail(countryName)[0]
        )
    }
}