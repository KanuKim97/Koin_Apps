package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.databinding.SelectCoinviewItemBinding
import com.example.koin_apps.viewModel.activity.SelectViewModel
import javax.inject.Inject

class SelectRecyclerAdapter(
    private var coinTitleList: List<String?>?,
): RecyclerView.Adapter<SelectRecyclerAdapter.CoinsViewHolder>() {
    private val selectedCoinList: MutableList<String> = mutableListOf()

    fun getSelectedItems(): List<String> = selectedCoinList

    inner class CoinsViewHolder(private val binding: SelectCoinviewItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind(coinTitle: String) {
                binding.titleCoin.text = coinTitle
                binding.checkCoin.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectedCoinList.add(coinTitle)
                    } else {
                        selectedCoinList.remove(coinTitle)
                    }
                }
            }
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
        holder.bind(coinTitleList?.get(position)!!.toString())

    override fun getItemCount(): Int { return coinTitleList?.size ?: 0 }

    override fun getItemViewType(position: Int): Int = position
}