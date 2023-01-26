package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.listViewAdapter.OrderBookAskListAdapter
import com.example.koin_apps.data.listViewAdapter.OrderBookBidListAdapter
import com.example.koin_apps.databinding.FragmentOrderBookBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.fragment.OrderBookViewModel

class OrderBookFragment : Fragment() {
    private lateinit var orderBookBinding: FragmentOrderBookBinding
    private lateinit var orderBookViewModel: OrderBookViewModel
    private lateinit var vmFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle = arguments?.getString("coinTitle")

        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        orderBookViewModel = ViewModelProvider(this, vmFactory)[OrderBookViewModel::class.java]

        orderBookViewModel.getOrderBookData(coinTitle!!, 5)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        orderBookBinding = FragmentOrderBookBinding.inflate(inflater, container, false)
        return orderBookBinding.root
    }

    override fun onResume() {
        super.onResume()

        orderBookViewModel.orderBookLiveData.observe(viewLifecycleOwner) { orderData->
            orderBookBinding.orderBookCurrency.text =
                getString(R.string.orderBook_Currency, orderData.order_currency)
            orderBookBinding.orderBookPaymentCurrency.text =
                getString(R.string.orderBook_paymentCurrency, orderData.payment_currency)
            orderBookBinding.OrderbookBidList.adapter = OrderBookBidListAdapter(orderData.bids!!)
            orderBookBinding.OrderBookAskList.adapter = OrderBookAskListAdapter(orderData.asks!!)
        }
    }

}