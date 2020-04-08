package com.vkhooda24.viewmodel.impl

import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.service.CountryService

/**
 * Created by Vikram Hooda on 2020-03-05.
 */
class CountriesListViewModelImpl : CountriesListViewModel {

    override suspend fun getCountriesList(regionName: String): List<Country> {
        return CountryService().getCountriesList(regionName)
    }
}
