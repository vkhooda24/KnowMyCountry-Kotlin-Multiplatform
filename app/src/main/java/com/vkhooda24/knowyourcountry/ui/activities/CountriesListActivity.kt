package com.vkhooda24.knowyourcountry.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.vkhooda24.CountryPresenter
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.app.AppConstants
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.knowyourcountry.ui.adapter.CountriesRecyclerAdapter
import com.vkhooda24.service.UICallback
import kotlinx.android.synthetic.main.activity_countries_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by Vikram Hooda on 12/22/18.
 */
class CountriesListActivity : Activity(), UICallback {
    override fun showError(error: Throwable) {
        Log.e("CountriesListActivity", error.message)
    }

    override fun countryListResponse(countryList: List<Country>) {

//        runOnUiThread {
        bindViewsData(countryList)
//        }
    }

    override fun countryDetailResponse(country: Country) {
    }

    private val countriesRecyclerAdapter: CountriesRecyclerAdapter by lazy {
        CountriesRecyclerAdapter()
    }

    //Nullable string i.e can assign null to this property
    private var regionName: String = AppConstants.REGION_ALL

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)

        //chain returns null if any of the properties in it is null
        //safe call operator ?.
        regionName = intent?.extras?.getString(IntentKeys.REGION_NAME) ?: AppConstants.REGION_ALL

        CountryPresenter(Dispatchers.Main, this).getCountryList(regionName)
    }

    private fun bindViewsData(countries: List<Country>) {

        //Create recycler adapter
        countriesRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        countriesRecyclerAdapter.setRecyclerData(countries)
        countriesRecyclerView.adapter = countriesRecyclerAdapter

        countriesCountTextView.text =
            getString(R.string.countries_list_heading, regionName, countries.size)

        countriesRecyclerAdapter.setOnItemClickListener(object :
            CountriesRecyclerAdapter.OnClickCountryName {
            override fun onClickItem(countryName: String?) {
                val intent = Intent(this@CountriesListActivity, CountryDetailActivity::class.java)
                intent.putExtra(IntentKeys.COUNTRY_NAME, countryName)
                startActivity(intent)
            }

        })
    }
}