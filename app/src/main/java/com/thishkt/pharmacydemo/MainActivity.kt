package com.thishkt.pharmacydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postPharmacyData()
//        getPharmacyData()

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
                Log.d("HKT", "onResponse: ${response.body?.string()}")
            }
        })

    }

    private fun postPharmacyData(){
        val testUrl =
            "https://reqres.in/api/users"

        //Part 1: 宣告 OkHttpClient
        val okHttpClient = OkHttpClient().newBuilder().build()

        //加入 FormBody 參數 name 和 job 。
        val formBody: FormBody = FormBody.Builder()
            .add("name", "HKT")
            .add("job", "Teacher")
            .build()

        //Part 2: 宣告 Request，要求要連到指定網址
        val request: Request = Request.Builder().url(testUrl).post(formBody).build()

        //Part 3: 宣告 Call
        val call = okHttpClient.newCall(request)

        //執行 Call 連線後，採用 enqueue 非同步方式，獲取到回應的結果資料
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("apple", "response: ${response.body?.string()}")
            }
        })
    }
}