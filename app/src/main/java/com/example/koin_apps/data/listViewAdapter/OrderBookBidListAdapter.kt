package com.example.koin_apps.data.listViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.orderBook.OrderAskBidData
import javax.inject.Inject

class OrderBookBidListAdapter @Inject constructor(
    private val orderBidData: ArrayList<OrderAskBidData>
): BaseAdapter() {
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null)
            convertView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.order_coinview_bids_item, parent, false)

        val orderBookPrice = convertView?.findViewById<TextView>(R.id.OrderBook_bid_price)
        val orderBookQuantity = convertView?.findViewById<TextView>(R.id.OrderBook_bid_quantity)

        orderBookPrice?.text = orderBidData[position].price
        orderBookQuantity?.text = orderBidData[position].quantity

        return convertView!!
    }

    override fun getCount(): Int = orderBidData.size

    override fun getItem(position: Int) = orderBidData[position]

    override fun getItemId(position: Int): Long  = position.toLong()
}