package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.requestError.RequestErrorRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectKoinActivity : AppCompatActivity() {
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding

    var mSelectKoin: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        setContentView(selectKoinBinding.root)
    }

    override fun onResume() {
        super.onResume()
        selectKoinResponse()
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

                        println(mSelectKoin?.data)
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

}