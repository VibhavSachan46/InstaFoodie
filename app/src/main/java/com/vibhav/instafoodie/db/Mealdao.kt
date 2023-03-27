package com.vibhav.instafoodie.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vibhav.instafoodie.pojo.Meal

@Dao
interface Mealdao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal: Meal)

    @Delete
    suspend fun delete(meal:Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals():LiveData<List<Meal>>


}