package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.Modifier
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.presenter.view.TickerChoicePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity() {
    private val selectKoinBinding by lazy { ActivitySelectKoinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectKoinBinding.SelectComposeView.setContent {
            TickerChoicePage(
                modifier = Modifier,
                onChoiceComplete = {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            )
        }

        setContentView(selectKoinBinding.root)
    }
}