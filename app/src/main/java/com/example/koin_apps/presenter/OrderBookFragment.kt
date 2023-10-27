package com.example.koin_apps.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.koin_apps.R
import com.example.koin_apps.presenter.adapter.OrderBookAskListAdapter
import com.example.koin_apps.presenter.adapter.OrderBookBidListAdapter
import com.example.koin_apps.presenter.adapter.TransactionListAdapter
import com.example.koin_apps.databinding.FragmentOrderBookBinding
import com.example.koin_apps.presenter.viewModel.OrderBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderBookFragment : Fragment() {
    private var _orderBookBinding: FragmentOrderBookBinding? = null
    private val orderBookBinding get() = _orderBookBinding!!
    private val orderBookViewModel: OrderBookViewModel by viewModels()

    private val ticker: String by lazy { setTicker() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderBookViewModel.fetchTickerData(ticker)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _orderBookBinding = FragmentOrderBookBinding.inflate(inflater, container, false)
        return orderBookBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        orderBookViewModel.tickerData.observe(viewLifecycleOwner) { result ->
            orderBookBinding.orderBookTickerInfo.text = getString(
                R.string.orderBook_Ticker_Info,
                result.closingPrice.toString(),
                result.prevClosingPrice.toString(),
                result.maxPrice.toString(),
                result.minPrice.toString(),
                result.unitsTraded_24H.toString()
            )
        }

        orderBookViewModel.transactionData.observe(viewLifecycleOwner) { result ->
            orderBookBinding.transactionList.adapter = TransactionListAdapter(result)
        }

        orderBookViewModel.orderBookData.observe(viewLifecycleOwner) { result ->
            orderBookBinding.orderBookAskList.adapter = OrderBookAskListAdapter(result.asks!!)
            orderBookBinding.orderBookBidList.adapter = OrderBookBidListAdapter(result.bids!!)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _orderBookBinding = null
    }

    private fun setTicker(): String = arguments?.getString("coinTitle").toString()

}