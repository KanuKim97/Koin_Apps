package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R

class RecyclerViewAdapter(private val coinTitleSet: Set<String?>?)
    : RecyclerView.Adapter<RecyclerViewAdapter.CoinTitleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinTitleViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.titlecoinlist, parent, false)

        return CoinTitleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinTitleViewHolder, position: Int) {
        val coinTitleList: Set<String?>? = coinTitleSet


    }

    override fun getItemCount(): Int {
        return coinTitleSet?.size!!
    }

    class CoinTitleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val checkList: CheckBox = itemView.findViewById(R.id.checkCoin)
        val coinTitle: TextView = itemView.findViewById(R.id.title_Coin)
    }
}