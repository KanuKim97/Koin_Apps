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
    private var selectViewModel: SelectViewModel
): RecyclerView.Adapter<SelectRecyclerAdapter.CoinsViewHolder>() {
    private val selectList: MutableList<String> = mutableListOf()

    class CoinsViewHolder(binding: SelectCoinviewItemBinding)
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
                .inflate(R.layout.select_coinview_item, parent, false)

        return CoinsViewHolder(SelectCoinviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val titleListPosition = coinTitleList?.get(position)

        holder.koinTitleData.text = titleListPosition
        holder.checkKoin.isChecked

        holder.checkKoin.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) { selectList.add(titleListPosition.toString()) }
            else { selectList.remove(titleListPosition.toString()) }

            selectViewModel.getData(selectList)
        }
    }

    override fun getItemCount(): Int { return coinTitleList?.size ?: 0 }

    override fun getItemViewType(position: Int): Int = position

}