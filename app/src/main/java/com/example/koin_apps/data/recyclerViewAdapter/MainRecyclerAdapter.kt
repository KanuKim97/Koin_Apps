package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.mainViewTicker.MainTickerData
import com.example.koin_apps.databinding.MainCoinviewItemBinding


class MainRecyclerAdapter(
    private var coinTitleList: List<MainTickerData>
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewTitleHolder>(){

    class MainViewTitleHolder(binding: MainCoinviewItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            val titleCoin = binding.titleCoin
            val rateFluctate24H = binding.ticker24HFlucatateRate
            val priceFluctate24H = binding.ticker24HFlucatate
            val prevCoinPrice = binding.tickerPrevClosingPrice
            val accTradeVal24H = binding.ticker24HAccTradeValue
            val unitsTrade24H = binding.ticker24HUnitsTraded
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
        holder.prevCoinPrice.text = coinDesc.ticker_Prev_Closing_Price
        holder.accTradeVal24H.text = coinDesc.ticker_24H_Acc_Trade_Value
        holder.unitsTrade24H.text = coinDesc.ticker_24H_Units_Traded
    }


    override fun getItemCount(): Int = coinTitleList.size

    override fun getItemViewType(position: Int): Int = position

}