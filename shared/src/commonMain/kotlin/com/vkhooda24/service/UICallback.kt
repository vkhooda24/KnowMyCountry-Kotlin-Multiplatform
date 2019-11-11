package com.vkhooda24.service

import com.vkhooda24.BaseView
import com.vkhooda24.knowyourcountry.model.Country

/**
 * Created by Vikram Hooda on 2019-11-09.
 */

interface UICallback : BaseView {
    fun countryListResponse(countryList: List<Country>)
    fun countryDetailResponse(countryDetail: Country)
}

