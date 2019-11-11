package com.vkhooda24.model

import com.vkhooda24.knowyourcountry.model.Country
import kotlinx.serialization.Serializable

/**
 * Created by Vikram Hooda on 2019-11-10.
 */
@Serializable
data class CountryList(
    val countryList: List<Country> = emptyList()
)