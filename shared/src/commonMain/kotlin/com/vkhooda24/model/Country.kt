package com.vkhooda24.knowyourcountry.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

/**
 * Created by Vikram Hooda on 12/27/18.
 */
@Serializable
data class Country(
    var name: String?,

    var nativeName: String?,

    var cioc: String?,

    var region: String?,

    var numericCode: String?,

    var callingCodes: Array<String>?,

    var alpha3Code: String?,

    var topLevelDomain: Array<String>?,

    var alpha2Code: String?,

    var capital: String?,

    var altSpellings: Array<String>?,

    var subregion: String?,

    var timezones: Array<String>?,

    var flag: String?,

    var area: String?,

    var latlng: Array<String>?,

    var demonym: String?,

    var gini: String?,

    var borders: Array<String>?,

    var population: String?
) {
    @Serializer(forClass = Country::class)
    object CountrySerializer : KSerializer<Country> {}
}
