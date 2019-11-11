package com.vkhooda24.service

import com.vkhooda24.BaseView
import com.vkhooda24.CoroutineScopePresenter
import com.vkhooda24.HttpClientEngine
import com.vkhooda24.domain.uiDispatcher
import com.vkhooda24.knowyourcountry.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Vikram Hooda on 2019-11-09.
 */

interface UiUpdate : BaseView {
    fun countryListResponse(countryList: List<Country>)
    fun countryDetailResponse(countryDetail: Country)
}

class CountryListViewModel {

}

