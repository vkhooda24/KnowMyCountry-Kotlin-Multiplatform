package com.vkhooda24.presenter

import com.vkhooda24.service.CountriesListResponseListener
import com.vkhooda24.viewmodel.impl.CountriesListViewModel
import com.vkhooda24.viewmodel.impl.CountriesListViewModelImpl
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Vikram Hooda on 2020-03-07.
 */
class CountriesListPresenter(
    context: CoroutineContext,
    private val countriesListResponseListener: CountriesListResponseListener
) : CoroutineScopePresenter(context, countriesListResponseListener) {

    private val countriesListViewModelImpl: CountriesListViewModel
    init {
        countriesListViewModelImpl = CountriesListViewModelImpl()
    }

    fun fetchCountiesList(regionName: String) = launch {
        val countriesList = countriesListViewModelImpl.getCountriesList(regionName)
        countriesListResponseListener.countriesListResponse(countriesList)
    }
}