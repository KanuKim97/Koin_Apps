package com.example.koin_apps.data.recyclerViewAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.databinding.TitlecoinlistBinding

class RecyclerViewAdapter(
    private var coinTitleList: List<String?>?
) : RecyclerView.Adapter<RecyclerViewAdapter.CoinsViewHolder>() {

    class CoinsViewHolder(private val binding: TitlecoinlistBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val checkKoin = binding.checkCoin
        val koinTitleData = binding.titleCoin
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
        val titleListPosition = coinTitleList?.get(position)
        holder.koinTitleData.text = titleListPosition
        holder.checkKoin.isChecked

        holder.checkKoin.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                Log.d("title Value", "$titleListPosition")
            }
        }

    }

    override fun getItemCount(): Int {
        return coinTitleList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}