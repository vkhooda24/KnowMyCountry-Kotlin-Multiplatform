package com.vkhooda24.knowyourcountry.ui.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import coil.api.load
import com.vkhooda24.presenter.CountryDetailsPresenter
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.app.AppConstants
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.service.CountryDetailsResponseListener
import com.vkhooda24.utils.StringUtil
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by Vikram Hooda on 2020-03-11
 */
class CountryDetailActivity : Activity(), CountryDetailsResponseListener {

    private var countryName: String = AppConstants.DEFAULT_COUNTRY_NAME

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        countryName =
            intent?.extras?.getString(IntentKeys.COUNTRY_NAME) ?: AppConstants.DEFAULT_COUNTRY_NAME

        CountryDetailsPresenter(Dispatchers.Main, this).fetchCountryDetails(countryName)
    }

    private fun setCountryDetails(countryDetail: Country) {
        val domainName = countryDetail.topLevelDomain?.get(0) ?: ""
        val timezone = countryDetail.timezones?.get(0) ?: ""
        countryNameTextView.text = countryDetail.name
        countryCapitalTextView.text = getString(R.string.capital, countryDetail.capital)
        countryNativeNameTextView.text =
            getString(R.string.native_name, countryDetail.nativeName)
        countryTopLevelDomainTextView.text = getString(R.string.domain_name, domainName)
        countryRegionTextView.text = getString(R.string.region_name, countryDetail.region)
        countrySubRegionTextView.text =
            getString(R.string.sub_region_name, countryDetail.subregion)
        countryTimeZoneTextView.text = getString(R.string.timezone, timezone)
        countryPopulationTextView.text =
            getString(
                R.string.population,
                StringUtil.formatNumberWithCommas(countryDetail.population?.get(0).toString())
            )
        countryAlphaNameTextView.text =
            getString(R.string.alpha_code_name, countryDetail.alpha3Code)

        countryFlagImageView.load(countryDetail.flag)
    }

    override fun countryDetailResponse(countryDetail: Country) {
        setCountryDetails(countryDetail)
    }

    override fun showError(error: Throwable) {
        Log.e("CountryDetailActivity", error.message)
    }
}