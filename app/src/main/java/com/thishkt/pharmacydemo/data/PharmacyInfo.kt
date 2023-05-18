package com.thishkt.pharmacydemo.data

import com.google.gson.annotations.SerializedName

class PharmacyInfo (
    // 有 serializedName 可自定義下方 value 名稱
    @SerializedName("features")
    val features: List<Feature>
)

class Feature(
    @SerializedName("properties")
    val property: Property
)

class Property(
    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String
)

//
//"features": [
//{
//    "type": "Feature",
//    "properties": {
//    "id": "5901010076",
//    "name": "榮星藥局",
//    "phone": "(02)27124696",
//    "address": "臺北市松山區南京東路４段１３３巷５弄１號",
//    "mask_adult": 0,
//    "mask_child": 2490,
//    "updated": "2022\/03\/09 22:53:23",
//    "available": "星期一上午看診、星期二上午看診、星期三上午看診、星期四上午看診、星期五上午看診、星期六上午看診、星期日上午看診、星期一下午看診、星期二下午看診、星期三下午看診、星期四下午看診、星期五下午看診、星期六下午看診、星期日下午看診、星期一晚上看診、星期二晚上看診、星期三晚上看診、星期四晚上看診、星期五晚上看診、星期六晚上看診、星期日晚上休診",
//    "note": "-",
//    "custom_note": "",
//    "website": "",
//    "county": "臺北市",
//    "town": "松山區",
//    "cunli": "東勢里",
//    "service_periods": "NNNNNNNNNNNNNNNNNNNNY"
//}