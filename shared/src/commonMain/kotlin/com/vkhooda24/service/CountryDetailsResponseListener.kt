package com.vkhooda24.service

import com.vkhooda24.BaseViewListener
import com.vkhooda24.knowyourcountry.model.Country

/**
 * Created by Vikram Hooda on 2020-03-07.
 */
interface CountryDetailsResponseListener : BaseViewListener {
    fun countryDetailResponse(countryDetail: Country)
}