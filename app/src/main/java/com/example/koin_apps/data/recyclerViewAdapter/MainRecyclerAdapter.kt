package com.example.koin_apps.data.recyclerViewAdapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.LiveTimeActivity
import com.example.koin_apps.data.remote.model.ticker.mainViewTicker.MainTickerData
import com.example.koin_apps.databinding.MainCoinviewItemBinding

class MainRecyclerAdapter(
    private var coinTitleList: ArrayList<MainTickerData>
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewItemHolder>() {

    inner class MainViewItemHolder(private val binding: MainCoinviewItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(mainTickerData: MainTickerData) {
            binding.titleCoin.text = mainTickerData.coinTitle
            binding.ticker24HFlucatate.text = mainTickerData.ticker_24H_Fluctate
            binding.ticker24HFlucatateRate.text = mainTickerData.ticker_24H_FluctateRate

            /*if(position != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    Intent(it.context, LiveTimeActivity::class.java).apply {
                        putExtra("coinTitle", mainTickerData.coinTitle)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.run { it.context.startActivity(this) }
                }
            }*/
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewItemHolder {
        val binding = MainCoinviewItemBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MainViewItemHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewItemHolder,
        position: Int
    ) {
        holder.bind(coinTitleList[position])
        holder.itemView.setOnClickListener {
            Intent(it.context, LiveTimeActivity::class.java)
                .apply { putExtra("coinTitle", coinTitleList[position].coinTitle) }
                .run { it.context.startActivity(this) }
        }

        Log.d(
            "Adapter Position",
            "$position : ${coinTitleList[position].coinTitle}"
        )
    }

    override fun getItemCount(): Int = coinTitleList.size

    override fun getItemViewType(position: Int): Int = position
}