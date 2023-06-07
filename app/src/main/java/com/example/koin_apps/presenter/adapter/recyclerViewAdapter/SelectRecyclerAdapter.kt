package com.example.koin_apps.presenter.adapter.recyclerViewAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.common.Constants
import com.example.koin_apps.databinding.SelectCoinviewItemBinding
import javax.inject.Inject

class SelectRecyclerAdapter @Inject constructor(
    private val tickerList: List<String?>?,
): RecyclerView.Adapter<SelectRecyclerAdapter.CoinsViewHolder>() {
    private val selectedTickerList: MutableList<String> = mutableListOf()

    inner class CoinsViewHolder(private val binding: SelectCoinviewItemBinding)
        :RecyclerView.ViewHolder(binding.root) {

        fun bind(ticker: String) {
            binding.titleCoin.text = ticker
            binding.checkCoin.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedTickerList.add(ticker)
                    Log.d(Constants.LOG_TAG, "$selectedTickerList")
                } else {
                    selectedTickerList.remove(ticker)
                    Log.d(Constants.LOG_TAG, "$selectedTickerList")
                }
            }
        }
    }

    fun getSelectedItems(): List<String> {
        return selectedTickerList.toList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinsViewHolder {
        val binding = SelectCoinviewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) =
        holder.bind(tickerList?.get(position)!!.toString())

    override fun getItemCount(): Int { return tickerList?.size ?: 0 }

    override fun getItemViewType(position: Int): Int = position
}