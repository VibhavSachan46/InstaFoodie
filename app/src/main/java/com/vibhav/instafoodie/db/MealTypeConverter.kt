package com.vibhav.instafoodie.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun fromAnytoString(attribute: Any?) : String{
        if(attribute == null)
            return ""
        return attribute as String
    }

    @TypeConverter
    fun fromStringtoAny(attribute: String?) : Any{
        if(attribute == null)
            return ""
        return attribute
    }
}