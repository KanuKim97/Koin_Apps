package com.example.koin_apps.data.listViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.orderBook.OrderAskBidData

class OrderBookAskListAdapter(
    private val orderAskData: ArrayList<OrderAskBidData>
): BaseAdapter() {
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if(convertView == null)
            convertView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.order_coinview_asks_item, parent, false)

        val orderBookAskPrice = convertView?.findViewById<TextView>(R.id.OrderBook_ask_price)
        val orderBookAskQuantity = convertView?.findViewById<TextView>(R.id.OrderBook_ask_quantity)

        orderBookAskPrice?.text = orderAskData[position].price
        orderBookAskQuantity?.text = orderAskData[position].quantity

        return convertView!!
    }

    override fun getCount(): Int = orderAskData.size

    override fun getItem(position: Int) = orderAskData[position]

    override fun getItemId(position: Int) = position.toLong()
}