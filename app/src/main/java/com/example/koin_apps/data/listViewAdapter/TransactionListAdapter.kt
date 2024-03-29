package com.example.koin_apps.data.listViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import javax.inject.Inject

class TransactionListAdapter @Inject constructor(
    private val transactionList: ArrayList<TransactionData>
): BaseAdapter() {
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if(convertView == null)
            convertView = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.transaction_coinview_item, parent, false)

        val transactionType = convertView?.findViewById<TextView>(R.id.transaction_Type)
        val transactionPrice = convertView?.findViewById<TextView>(R.id.transaction_Price)
        val transactionUnits = convertView?.findViewById<TextView>(R.id.transaction_Units)

        transactionType?.text = transactionList[position].type
        transactionPrice?.text = transactionList[position].price
        transactionUnits?.text = transactionList[position].units_traded

        return convertView!!
    }

    override fun getCount(): Int = transactionList.size

    override fun getItem(position: Int) = transactionList[position]

    override fun getItemId(position: Int): Long = position.toLong()
}