package com.vibhav.instafoodie.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {



//    lateinit var api:Mealapi
//
//    init {
//         Ret
//    }

    val api:Mealapi by lazy{
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Mealapi::class.java)
    }
}
