package com.vkhooda24.viewmodel

import com.vkhooda24.knowyourcountry.model.Country

/**
 * Created by Vikram Hooda on 2020-03-05.
 */
interface CountryDetailsViewModel {
    suspend fun getCountryDetail(countryName: String): Country
}