package com.vkhooda24.knowyourcountry.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

/**
 * Created by Vikram Hooda on 2020-03-05.
 */
@Serializable
data class Country(
    var name: String?,

    var nativeName: String?,

    var cioc: String?,

    var region: String?,

    var numericCode: String?,

    var callingCodes: MutableList<String>?,

    var alpha3Code: String?,

    var topLevelDomain: MutableList<String>?,

    var alpha2Code: String?,

    var capital: String?,

    var altSpellings: MutableList<String>?,

    var subregion: String?,

    var timezones: MutableList<String>?,

    var flag: String?,

    var area: String?,

    var latlng: MutableList<String>?,

    var demonym: String?,

    var gini: String?,

    var borders: MutableList<String>?,

    var population: String?
) {
    @Serializer(forClass = Country::class)
    object CountrySerializer : KSerializer<Country> {}
}
