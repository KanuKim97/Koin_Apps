package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.koin_apps.databinding.FragmentOrderBookBinding

class OrderBookFragment : Fragment() {
    private lateinit var orderBookBinding: FragmentOrderBookBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        orderBookBinding = FragmentOrderBookBinding.inflate(inflater, container, false)
        return orderBookBinding.root
    }

}