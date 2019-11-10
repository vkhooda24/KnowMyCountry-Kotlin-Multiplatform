package com.vkhooda24.knowyourcountry.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vkhooda24.knowyourcountry.R
import com.vkhooda24.knowyourcountry.model.Country

/**
 * Created by Vikram Hooda on 12/23/18.
 */
class CountriesRecyclerAdapter :
    androidx.recyclerview.widget.RecyclerView.Adapter<CountriesRecyclerAdapter.RecyclerViewHolder>() {
    private var countriesList: List<Country> = emptyList()

    private var onItemClickListener: OnClickCountryName? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_countries_recycler_view, viewGroup, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(recyclerViewHolder: RecyclerViewHolder, position: Int) {

        val countryName = countriesList[position].name
        recyclerViewHolder.countryNameTextView.text = countryName

        recyclerViewHolder.itemView.setOnClickListener {
            onItemClickListener?.onClickItem(
                countryName
            )
        }
    }

    override fun getItemCount() = countriesList.size

    inner class RecyclerViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)
    }

    fun setOnItemClickListener(onItemClickListener: OnClickCountryName) {
        this.onItemClickListener = onItemClickListener
    }

    fun setRecyclerData(countries: List<Country>) {
        this.countriesList = countries
    }

    interface OnClickCountryName {
        fun onClickItem(countryName: String?)
    }
}