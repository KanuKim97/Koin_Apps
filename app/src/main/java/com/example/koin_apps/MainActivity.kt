package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.readAllCoinData.observe(this) {
            if (it.isNullOrEmpty()) {
                showDataNullDialog()
            } else {
                mainActivityBinding.mainRecyclerView.adapter = MainRecyclerAdapter(this, it)
            }
        }

        mainActivityBinding.addCoinBtn.setOnClickListener {
            startActivity(Intent(this, SelectKoinActivity::class.java))
        }
    }

    private fun showDataNullDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(getString(R.string.NullDialog_Title))
            .setMessage(getString(R.string.NullDialog_Content))
            .setIcon(R.drawable.ic_dangerous)
            .setPositiveButton(getString(R.string.NullDialog_okBtn)) {_, _ ->}
            .create()
            .show()
    }

}