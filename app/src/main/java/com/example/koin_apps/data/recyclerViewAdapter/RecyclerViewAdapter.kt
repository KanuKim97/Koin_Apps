package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.tickerTitle.KoinTitleData

class RecyclerViewAdapter
    : RecyclerView.Adapter<RecyclerViewAdapter.CoinsViewHolder>() {

    private val koinTitleList: List<KoinTitleData>? = null

    class CoinsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(){

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinsViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.titlecoinlist, parent, false)

        return CoinsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return koinTitleList?.size ?: 0
    }

}