package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.koin_apps.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private lateinit var TransactionBinding: FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TransactionBinding = FragmentTransactionBinding.inflate(inflater, container, false)
        return TransactionBinding.root
    }

}