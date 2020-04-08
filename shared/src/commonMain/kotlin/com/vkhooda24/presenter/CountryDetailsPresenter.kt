package com.vkhooda24.presenter

import com.vkhooda24.service.CountryDetailsResponseListener
import com.vkhooda24.viewmodel.CountryDetailsViewModel
import com.vkhooda24.viewmodel.impl.CountryDetailsViewModelImpl
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2020-03-07.
 */
class CountryDetailsPresenter(
    context: CoroutineContext,
    private val countryDetailsResponseListener: CountryDetailsResponseListener
) : CoroutineScopePresenter(context, countryDetailsResponseListener) {

    private val countryDetailsViewModel: CountryDetailsViewModel

    init {
        countryDetailsViewModel = CountryDetailsViewModelImpl()
    }

    fun fetchCountryDetails(countryName: String) = launch {
        countryDetailsResponseListener.countryDetailResponse(
            countryDetailsViewModel.getCountryDetail(countryName)
        )
    }
}