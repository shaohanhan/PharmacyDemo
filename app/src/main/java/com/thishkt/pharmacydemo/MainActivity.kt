package com.thishkt.pharmacydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.thishkt.pharmacydemo.data.PharmacyInfo
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPharmacyData()

    }

    private fun getPharmacyData() {
        //口罩資料網址
        val pharmaciesDataUrl =
            "https://raw.githubusercontent.com/kiang/pharmacies/master/json/points.json"
//            "https://raw.githubusercontent.com/thishkt/pharmacies/master/data/info.json"

        //Part 1: 宣告 OkHttpClient
        val okHttpClient = OkHttpClient().newBuilder().build()

        //Part 2: 宣告 Request，要求要連到指定網址
        val request: Request = Request.Builder().url(pharmaciesDataUrl).get().build()

        //Part 3: 宣告 Call
        val call = okHttpClient.newCall(request)

        //執行 Call 連線後，採用 enqueue 非同步方式，獲取到回應的結果資料
        call.enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.e("HKT", "onFailure: $e")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val pharmaciesData = response.body?.string()

                val pharmacyInfo = Gson().fromJson(pharmaciesData, PharmacyInfo::class.java)
                for (i in pharmacyInfo.features) {
                    Log.d("HKT", "name: ${i.property.name}, phone ${i.property.phone}")
                }
            }
        })

    }
}