package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.data.remote.model.tickerTitle.TickerTitleData
import com.example.koin_apps.databinding.TitlecoinlistBinding

class RecyclerViewAdapter
    : ListAdapter<TickerTitleData, RecyclerViewAdapter.CoinsViewHolder>(DiffCallback()) {

    class CoinsViewHolder(private val binding: TitlecoinlistBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(tickerTitleData: TickerTitleData) {
            binding.apply {
                checkCoin.isChecked = tickerTitleData.checked
                titleCoin.text = tickerTitleData.tickerTitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding =
            TitlecoinlistBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currItem = getItem(position)
        holder.bind(currItem)
    }

    class DiffCallback: DiffUtil.ItemCallback<TickerTitleData>() {
        override fun areItemsTheSame(oldItem: TickerTitleData, newItem: TickerTitleData) =
            oldItem.tickerTitle == newItem.tickerTitle

        override fun areContentsTheSame(
            oldItem: TickerTitleData,
            newItem: TickerTitleData
        ) = oldItem == newItem

    }
}