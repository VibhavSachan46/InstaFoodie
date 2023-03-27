package com.vibhav.instafoodie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vibhav.instafoodie.pojo.Meal

@Database(entities = arrayOf(Meal::class), version=1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): Mealdao

    companion object{
        @Volatile
        var INSTANCE:MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context):MealDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }
}