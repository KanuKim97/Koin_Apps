package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.di.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.data.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher
    @Inject lateinit var alertDialog: AlertDialog.Builder

    private lateinit var mainActivityBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        updateUI()

        mainActivityBinding.addCoinBtn.setOnClickListener {
            startActivity(Intent(this, SelectKoinActivity::class.java))
        }

        setContentView(mainActivityBinding.root)
    }

    private fun updateUI() {
        mainViewModel.readAllTicker.observe(this) { ticker ->
            if(ticker != null) {
                lifecycleScope.launch(mainDispatcher) {
                    mainActivityBinding.mainRecyclerView.adapter =
                        MainRecyclerAdapter(this@MainActivity, ticker)
                }
            } else {
                lifecycleScope.launch(mainDispatcher) { showDataNullDialog() }
            }
        }
    }

    private fun showDataNullDialog(): AlertDialog =
        alertDialog
            .setIcon(R.drawable.ic_dangerous)
            .setTitle(R.string.NullDialog_Title)
            .setMessage(R.string.NullDialog_Content)
            .setPositiveButton(R.string.NullDialog_okBtn, null)
            .show()

}