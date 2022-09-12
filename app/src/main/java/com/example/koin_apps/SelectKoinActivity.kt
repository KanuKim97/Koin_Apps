package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.recyclerViewAdapter.RecyclerViewAdapter
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.SelectViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectKoinActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding
    private lateinit var selectViewModel: SelectViewModel

    var mSelectKoin: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectViewModel = ViewModelProvider(this)[SelectViewModel::class.java]
        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        val coinViewAdapter = RecyclerViewAdapter()

        selectKoinBinding.apply {
            CoinRecyclerView.apply {
                adapter = coinViewAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }

        setContentView(selectKoinBinding.root)
    }

    override fun onResume() {
        super.onResume()
        selectKoinResponse()

        /*
        selectViewModel.selectKoinList.observe(
            this, { koinNameList ->

        })
        */

        selectKoinBinding.compSelectBtn.setOnClickListener(this)
    }


    private fun selectKoinResponse() {
        val mSelectKoinResponse = RetrofitRepo.getTickerSingleton("ALL")

        mSelectKoinResponse.enqueue(object: Callback<TickerRoot> {
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {

                when(response.code()) {

                    200 -> {
                        mSelectKoin = response.body()
                        selectViewModel.updateSelectValue(mSelectKoin?.data?.keys)
                    }

                    400 -> {
                        val jsonObject: JSONObject

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseErrorCode = jsonObject.getString("status")
                            val responseErrorMsg = jsonObject.getString("message")

                            selectViewModel.updateResponseError(responseErrorCode, responseErrorMsg)
                        } catch (e: JSONException) { e.printStackTrace() }


                    }

                }

            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    //TODO select Page to go Main Page
    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.compSelectBtn -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }
    }

}