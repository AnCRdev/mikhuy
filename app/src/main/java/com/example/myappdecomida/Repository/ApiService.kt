package com.example.myappdecomida

import com.example.myappdecomida.comida.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class ApiService {
    private val client = OkHttpClient()
    private val gson = Gson()

    @Throws(IOException::class)
    fun fetchMeals(): List<Meal> {
        val request = Request.Builder()
            .url("https://www.themealdb.com/api/json/v1/1/search.php?s=Chicken")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val responseData = response.body!!.string()
            val jsonObject = JSONObject(responseData)
            val mealsArray = jsonObject.getJSONArray("meals")
            val mealType = object : TypeToken<List<Meal>>() {}.type
            return gson.fromJson(mealsArray.toString(), mealType)
        }
    }
}