package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.koin_apps.data.listViewAdapter.TransactionListAdapter
import com.example.koin_apps.databinding.FragmentTransactionBinding
import com.example.koin_apps.viewModel.fragment.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionFragment : Fragment() {
    private lateinit var transactionBinding: FragmentTransactionBinding
    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle = arguments?.getString("coinTitle")
        transactionViewModel.setOrderParameter(coinTitle.toString())
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

        transactionViewModel.transactionLiveData.observe(this) {
            transactionBinding.TransactionList.adapter = TransactionListAdapter(it)
        }
    }

}