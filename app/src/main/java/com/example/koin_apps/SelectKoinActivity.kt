package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.requestError.RequestErrorRoot
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
        setContentView(selectKoinBinding.root)
    }

    override fun onResume() {
        super.onResume()
        selectKoinResponse()

        selectViewModel.selectKoinList.observe(
            this, { koinNameList ->

            })

        selectKoinBinding.compSelectBtn.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
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

                    //response Result
                        Log.d("response Status: ", "${mSelectKoin?.status}")
                        Log.d("response Message: ", "${mSelectKoin?.message}")
                        Log.d("response Data: ", "${mSelectKoin?.data}")
                        Log.d("response Data(Key): ", "${mSelectKoin?.data?.keys}")
                    //---------------

                    }

                    400 -> {
                        val jsonObject: JSONObject

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")
                            val selectError = RequestErrorRoot(responseCode, responseMsg)

                            println(selectError)

                        } catch (e: JSONException) { e.printStackTrace() }


                    }

                }

            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    //ToDo select Page to go Main Page
    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.compSelectBtn -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }
    }

}