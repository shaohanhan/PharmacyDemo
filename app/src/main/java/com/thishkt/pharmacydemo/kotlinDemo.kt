package com.thishkt.pharmacydemo

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

fun main(){
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

//        //執行 Call 連線後，採用 enqueue 非同步方式，獲取到回應的結果資料
//        call.enqueue(object : Callback {
//            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
//                Log.e("HKT", "onFailure: $e")
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: Response) {
//                Log.d("HKT", "onResponse: ${response.body?.string()}")
//            }
//        })

    // 測試採用 execute 同步的方式， 錯誤  Caused by: android.os.NetworkOnMainThreadException
    val result: Response = call.execute()
    println("onResponse: ${result.body?.string()}")

}
