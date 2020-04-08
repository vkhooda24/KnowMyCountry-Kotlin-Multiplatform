package com.vkhooda24.knowyourcountry.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.vkhooda24.presenter.CountriesListPresenter
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.app.AppConstants
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.knowyourcountry.ui.adapter.CountriesRecyclerAdapter
import com.vkhooda24.service.CountriesListResponseListener
import kotlinx.android.synthetic.main.activity_countries_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by Vikram Hooda on 03/01/2020
 */
class CountriesListActivity : Activity(), CountriesListResponseListener {

    //Nullable string i.e can assign null to this property
    private var regionName: String = AppConstants.REGION_ALL
    private val countriesRecyclerAdapter: CountriesRecyclerAdapter by lazy {
        CountriesRecyclerAdapter()
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)
        progressBarView.visibility= View.VISIBLE

        //chain returns null if any of the properties in it is null
        //safe call operator ?.
        regionName = intent?.extras?.getString(IntentKeys.REGION_NAME) ?: AppConstants.REGION_ALL
        countriesCountTextView.text =
            getString(R.string.countries_list_heading, regionName)

        CountriesListPresenter(Dispatchers.Main, this).fetchCountiesList(regionName)
    }

    private fun bindViewsData(countries: List<Country>) {

        //Create recycler adapter
        countriesRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        countriesRecyclerAdapter.setRecyclerData(countries)
        countriesRecyclerView.adapter = countriesRecyclerAdapter

        countriesCountTextView.text =
            getString(R.string.countries_list_count_heading, regionName, countries.size)

        countriesRecyclerAdapter.setOnItemClickListener(object :
            CountriesRecyclerAdapter.OnClickCountryName {
            override fun onClickItem(countryName: String?) {
                val intent = Intent(this@CountriesListActivity, CountryDetailActivity::class.java)
                intent.putExtra(IntentKeys.COUNTRY_NAME, countryName)
                startActivity(intent)
            }
        })
    }

    override fun countriesListResponse(countriesList: List<Country>) {
        progressBarView.visibility= View.GONE
        bindViewsData(countriesList)
    }

    override fun showError(error: Throwable) {
        progressBarView.visibility= View.GONE
        Log.e("CountriesListActivity", error.message)
    }
}