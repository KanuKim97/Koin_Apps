package com.example.koin_apps.presenter.adapter.listViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.domain.entity.OrderAskBidEntity
import com.example.koin_apps.R
import javax.inject.Inject

class OrderBookAskListAdapter @Inject constructor(
    private val orderAskList: List<OrderAskBidEntity>
): BaseAdapter() {
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if(convertView == null)
            convertView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.order_coinview_asks_item, parent, false)

        val orderBookAskPrice = convertView?.findViewById<TextView>(R.id.OrderBook_ask_price)
        val orderBookAskQuantity = convertView?.findViewById<TextView>(R.id.OrderBook_ask_quantity)

        orderBookAskPrice?.text = orderAskList[position].price
        orderBookAskQuantity?.text = orderAskList[position].quantity

        return convertView!!
    }

    override fun getCount(): Int = orderAskList.size

    override fun getItem(position: Int) = orderAskList[position]

    override fun getItemId(position: Int) = position.toLong()
}