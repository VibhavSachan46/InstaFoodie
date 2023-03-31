package com.vibhav.instafoodie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vibhav.instafoodie.db.MealDatabase

class HomeViewModelFactory(
    private val mealDatabase: MealDatabase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeviewModel(mealDatabase) as T
    }
}