package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.koin_apps.presenter.viewModel.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import com.example.koin_apps.presenter.view.TickerListPage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainActivityBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTickerDB()

        mainActivityBinding.mainComposeView.setContent {
            TickerListPage(
                modifier = Modifier,
                onAddClick = {
                    startActivity(Intent(this, SelectKoinActivity::class.java))
                }
            )
        }

        setContentView(mainActivityBinding.root)
    }

    private fun fetchTickerDB(): Job = mainViewModel.fetchAllTickerData()
}