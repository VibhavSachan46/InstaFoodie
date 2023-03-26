package com.vibhav.instafoodie.retrofit

import com.vibhav.instafoodie.pojo.CategoryList
import com.vibhav.instafoodie.pojo.MealsByCategoryList
import com.vibhav.instafoodie.pojo.MealList
import com.vibhav.instafoodie.pojo.MealsByCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Mealapi {

    @GET("random.php")
    fun getRandoMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<MealsByCategoryList>


    @GET("categories.php")
    fun getCategoryList() : Call<CategoryList>

    @GET("filter.php")
    fun getmealsByCategory(@Query("c") categoryName: String) : Call<MealsByCategoryList>

}