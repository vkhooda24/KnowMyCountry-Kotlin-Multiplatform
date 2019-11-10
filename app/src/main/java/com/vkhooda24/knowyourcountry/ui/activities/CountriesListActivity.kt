package com.vkhooda24.knowyourcountry.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.app.AppConstants
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import com.vkhooda24.knowyourcountry.model.Country
import com.vkhooda24.knowyourcountry.ui.adapter.CountriesRecyclerAdapter
import com.vkhooda24.service.CountryListViewModel
import kotlinx.android.synthetic.main.activity_countries_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Vikram Hooda on 12/22/18.
 */
class CountriesListActivity : Activity() {

    private val countryListViewModel: CountryListViewModel by lazy {
        CountryListViewModel()
    }

    private val countriesRecyclerAdapter: CountriesRecyclerAdapter by lazy {
        CountriesRecyclerAdapter()
    }

    //Nullable string i.e can assign null to this property
    private var regionName: String = AppConstants.REGION_ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries_list)

        //chain returns null if any of the properties in it is null
        //safe call operator ?.
        regionName = intent?.extras?.getString(IntentKeys.REGION_NAME) ?: AppConstants.REGION_ALL

        CoroutineScope(Dispatchers.Main).launch {
            val countryList = countryListViewModel.getCountryList(regionName)
            bindViewsData(countryList)
        }
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