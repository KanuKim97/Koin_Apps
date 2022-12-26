package com.example.koin_apps.data.recyclerViewAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.LiveTimeActivity
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.mainViewTicker.MainTickerData
import com.example.koin_apps.databinding.MainCoinviewItemBinding

class MainRecyclerAdapter(
    private var coinTitleList: ArrayList<MainTickerData>
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewTitleHolder>(){

    inner class MainViewTitleHolder(binding: MainCoinviewItemBinding)
        : RecyclerView.ViewHolder (binding.root) {
        val titleCoin = binding.titleCoin
        val priceFluctate24H = binding.ticker24HFlucatate
        val rateFluctate24H = binding.ticker24HFlucatateRate
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewTitleHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_coinview_item, parent, false)

        return MainViewTitleHolder(MainCoinviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MainViewTitleHolder, position: Int) {
        val coinDesc = coinTitleList[position]
        holder.titleCoin.text = coinDesc.coinTitle
        holder.rateFluctate24H.text = coinDesc.ticker_24H_FluctateRate
        holder.priceFluctate24H.text = coinDesc.ticker_24H_Fluctate

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,  LiveTimeActivity::class.java)
            intent.putExtra("coinTitle", coinDesc.coinTitle)
            intent.run { it.context.startActivity(intent) }
        }
    }

    override fun getItemCount(): Int = coinTitleList.size

    override fun getItemViewType(position: Int): Int = position

}