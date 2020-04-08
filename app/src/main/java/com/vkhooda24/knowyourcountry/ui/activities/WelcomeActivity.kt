package com.vkhooda24.knowyourcountry.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.constants.IntentKeys
import com.vkhooda24.platformName
import com.vkhooda24.utils.*
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * Created by Vikram Hooda on 03/01/2020
 */
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        plateformName.text = platformName

        allCountriesButton.setOnClickListener {
            launchCountriesListActivity(ALL)
        }

        asiaRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity(ASIA)
        }

        americasRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity(AMERICAS)
        }

        europeRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity(EUROPE)
        }

        africaRegionCountriesButton.setOnClickListener {
            launchCountriesListActivity(AFRICA)
        }

        //Object expression
        oceaniaRegionCountriesButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                launchCountriesListActivity(OCEANIA)
            }
        })
    }

    private fun launchCountriesListActivity(regionName: String) {
        intent = Intent(this, CountriesListActivity::class.java)
        intent.putExtra(IntentKeys.REGION_NAME, regionName)
        startActivity(intent)
    }
}