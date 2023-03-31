package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.databinding.ActivityLogoBinding
import com.example.koin_apps.viewModel.activity.LogoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LogoActivity : AppCompatActivity() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher
    private lateinit var logoBinding: ActivityLogoBinding
    private val logoViewModel: LogoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logoBinding = ActivityLogoBinding.inflate(layoutInflater)

        logoViewModel.readAllData.observe(this) { result ->
            lifecycleScope.launch(mainDispatcher) {
                if (result != null) {
                    delay(Constants.DelayTimeMillis)
                    startActivity(Intent(this@LogoActivity, MainActivity::class.java))
                    finish()
                } else {
                    delay(Constants.DelayTimeMillis)
                    startActivity(Intent(this@LogoActivity, SelectKoinActivity::class.java))
                    finish()
                }
            }
        }

        setContentView(logoBinding.root)
    }
}