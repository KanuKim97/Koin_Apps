package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.tickerTitle.KoinTitleData
import com.example.koin_apps.databinding.TitlecoinlistBinding

class RecyclerViewAdapter
    : RecyclerView.Adapter<RecyclerViewAdapter.CoinsViewHolder>() {

    private val koinTitleList: List<KoinTitleData>? = null

    class CoinsViewHolder(private val binding: TitlecoinlistBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val koinTitleData = binding.titleCoin

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

        return CoinsViewHolder(TitlecoinlistBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        holder.koinTitleData.text = koinTitleList?.get(position)?.tickerTitle
    }

    override fun getItemCount(): Int {
        return koinTitleList?.size ?: 0
    }

}