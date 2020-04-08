package com.vkhooda24.viewmodel.impl

import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.service.CountryService
import com.vkhooda24.viewmodel.CountryDetailsViewModel

/**
 * Created by Vikram Hooda on 2020-03-05.
 */
class CountryDetailsViewModelImpl : CountryDetailsViewModel {
    override suspend fun getCountryDetail(countryName: String): Country {
        val countryDetail = CountryService().getCountryDetail(countryName)
        return countryDetail.isNotEmpty().let {
            countryDetail[0]
        }
    }
}   