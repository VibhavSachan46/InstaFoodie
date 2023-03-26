package com.vibhav.instafoodie.retrofit

import com.vibhav.instafoodie.pojo.CategoryList
import com.vibhav.instafoodie.pojo.CategoryMeals
import com.vibhav.instafoodie.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.IDN

interface Mealapi {

    @GET("random.php")
    fun getRandoMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<CategoryList>
}