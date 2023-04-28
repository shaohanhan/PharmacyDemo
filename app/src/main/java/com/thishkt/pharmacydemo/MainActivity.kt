package com.thishkt.pharmacydemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.thishkt.pharmacydemo.databinding.ActivityMainBinding
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPharmacyData()

    }

    private fun getPharmacyData() {
        //口罩資料網址
        val pharmaciesDataUrl =
            "https://raw.githubusercontent.com/kiang/pharmacies/master/json/points.json"

        //Part 1: 宣告 OkHttpClient
        val okHttpClient = OkHttpClient().newBuilder().build()

        //Part 2: 宣告 Request，要求要連到指定網址
        val request: Request = Request.Builder().url(pharmaciesDataUrl).get().build()

        //Part 3: 宣告 Call
        val call = okHttpClient.newCall(request)

        //執行 Call 連線後，採用 enqueue 非同步方式，獲取到回應的結果資料
        call.enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                Log.d("HKT", "onFailure: $e")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val pharmaciesData : String? = response.body?.string()
                // 將資料轉換成json
                val obj = JSONObject(pharmaciesData)
                //features 是一個陣列 [] ，需要將他轉換成 JSONArray
                val featuresArray = JSONArray(obj.getString("features"))
                //Log.d("HKT", "type: ${obj.getString("type")}")

                //藥局名稱變數宣告
                val propertiesName = StringBuilder()
                //迴圈取 array 資料
                for (i in 0 until featuresArray.length()){
                    val properties = featuresArray.getJSONObject(i).getString("properties")
                    val propertiesObj = JSONObject(properties)

                    //將每次獲取到的藥局名稱，多加跳行符號，存到變數中
                    propertiesName.append(propertiesObj.getString("name") + "\n")
                }

                runOnUiThread{
                    //將 Okhttp 獲取到的回應值，指定到畫面的 TextView 元件中
                    binding.tvPharmaciesData.text= propertiesName
                }
            }
        })

    }
}