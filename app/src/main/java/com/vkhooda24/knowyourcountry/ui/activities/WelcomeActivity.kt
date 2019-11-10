package com.vkhooda24.knowyourcountry.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        allCountriesButton.setOnClickListener {
            launchCountriesListActivity("All")
        }

        asiaRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity("Asia")
        }

        americasRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity("Americas")
        }

        europeRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity("Europe")
        }

        africaRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity("Africa")
        }

        //Object expression
        oceaniaRegionCountriesButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                launchCountriesListActivity("Oceania")
            }
        })
    }

    private fun launchCountriesListActivity(regionName: String) {
        intent = Intent(this, CountriesListActivity::class.java)
        intent.putExtra(IntentKeys.REGION_NAME, regionName)
        startActivity(intent)
    }
}