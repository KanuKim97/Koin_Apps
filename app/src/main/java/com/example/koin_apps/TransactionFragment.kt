package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.FragmentTransactionBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.fragment.OrderBookViewModel
import com.example.koin_apps.viewModel.fragment.TransactionViewModel

class TransactionFragment : Fragment() {
    private lateinit var transactionBinding: FragmentTransactionBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        transactionViewModel = ViewModelProvider(this, vmFactory)[TransactionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        transactionBinding = FragmentTransactionBinding.inflate(inflater, container, false)
        return transactionBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

}