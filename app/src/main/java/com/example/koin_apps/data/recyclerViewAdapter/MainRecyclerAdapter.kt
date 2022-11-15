package com.example.koin_apps.data.recyclerViewAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.databinding.MainCoinviewItemBinding
import com.example.koin_apps.viewModel.activity.MainViewModel

class MainRecyclerAdapter(
    private var mainViewModel: MainViewModel
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewTitleHolder>(){

    class MainViewTitleHolder(binding: MainCoinviewItemBinding)
        : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewTitleHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainViewTitleHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int = position

}