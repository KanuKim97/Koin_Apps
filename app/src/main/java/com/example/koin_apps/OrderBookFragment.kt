package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.koin_apps.data.listViewAdapter.OrderBookAskListAdapter
import com.example.koin_apps.data.listViewAdapter.OrderBookBidListAdapter
import com.example.koin_apps.data.listViewAdapter.TransactionListAdapter
import com.example.koin_apps.databinding.FragmentOrderBookBinding
import com.example.koin_apps.viewModel.fragment.OrderBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderBookFragment : Fragment() {
    private var _orderBookBinding: FragmentOrderBookBinding? = null
    private val orderBookBinding get() = _orderBookBinding!!
    private val orderBookViewModel: OrderBookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle: String = arguments?.getString("coinTitle").toString()
        orderBookViewModel.loadTickerInfo(coinTitle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _orderBookBinding = FragmentOrderBookBinding.inflate(inflater, container, false)

        orderBookViewModel.tickerLiveData.observe(viewLifecycleOwner) {
            orderBookBinding.orderBookTickerInfo.text =
                getString(
                    R.string.orderBook_Ticker_Info,
                    it.closingPrice,
                    it.prevClosingPrice,
                    it.maxPrice,
                    it.minPrice,
                    it.unitsTraded
                )
        }

        orderBookViewModel.transactionLiveData.observe(viewLifecycleOwner) {
            orderBookBinding.transactionList.adapter = TransactionListAdapter(it)
        }

        orderBookViewModel.orderBookLiveData.observe(viewLifecycleOwner) {
            orderBookBinding.orderBookAskList.adapter = OrderBookAskListAdapter(it.asks!!)
            orderBookBinding.orderBookBidList.adapter = OrderBookBidListAdapter(it.bids!!)
        }

        return orderBookBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _orderBookBinding = null
    }
}