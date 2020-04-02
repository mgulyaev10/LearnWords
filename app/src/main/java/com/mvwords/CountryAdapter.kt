package com.mvwords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    callback: LanguageChooseListener
): RecyclerView.Adapter<CountryHolder>() {

    private val languagesProvider = LanguagesProvider()
    private val countryClickListener = object : CountryHolder.CountryClickListener {
        override fun onClick(position: Int) {
            callback.onLanguageSelect(languagesProvider.languages[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_holder, parent, false)
        return CountryHolder(view, countryClickListener)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(languagesProvider.languages[position])
    }

    override fun getItemCount(): Int = languagesProvider.getCount()

}