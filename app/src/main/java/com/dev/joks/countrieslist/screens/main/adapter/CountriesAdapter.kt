package com.dev.joks.countrieslist.screens.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.joks.countrieslist.R
import com.dev.joks.countrieslist.screens.main.MainActivity
import com.dev.joks.countrieslist.service.model.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.list_item_country.view.*

class CountriesAdapter(
    private val context: Context?,
    private val items: MutableList<Country>
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    var onItemClick: ((Country) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<Country>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_country, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(item: Country) {
            with(itemView) {
                GlideToVectorYou.justLoadImage(context as MainActivity, Uri.parse(item.flagUrl), ivFlag)
                tvName.text = String.format("%s (%s)", item.name, item.nativeName)
                tvPopulation.text = item.population.toString()
            }
        }
    }
}