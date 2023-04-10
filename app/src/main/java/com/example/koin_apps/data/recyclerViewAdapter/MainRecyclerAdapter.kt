package com.example.koin_apps.data.recyclerViewAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.LiveTimeActivity
import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.databinding.MainCoinviewItemBinding
import javax.inject.Inject

class MainRecyclerAdapter @Inject constructor(
    private val context: Context,
    private val tickerList: List<TickerEntity>
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewItemHolder>() {

    inner class MainViewItemHolder(private val binding: MainCoinviewItemBinding)
        :RecyclerView.ViewHolder(binding.root) {

        fun bind(tickerEntity: TickerEntity) {
            binding.titleCoin.text = tickerEntity.Ticker
            binding.NavigateCoinDesc.setOnClickListener {
                Intent(context, LiveTimeActivity::class.java)
                    .putExtra("coinTitle", tickerEntity.Ticker)
                    .run { context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewItemHolder {
        val binding = MainCoinviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewItemHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewItemHolder, position: Int) =
        holder.bind(tickerList[position])

    override fun getItemCount(): Int = tickerList.size

    override fun getItemViewType(position: Int): Int = position
}