package com.vibhav.instafoodie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.vibhav.instafoodie.Adapter.CategoryMealAdapter
import com.vibhav.instafoodie.R
import com.vibhav.instafoodie.databinding.ActivityCategoryMealsBinding
import com.vibhav.instafoodie.fragment.HomeFragment
import com.vibhav.instafoodie.viewmodel.CategoryMealViewModel

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var categoryMealsViewModel: CategoryMealViewModel
    lateinit var categoryMealsAdapter:CategoryMealAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate((layoutInflater))
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealViewModel::class.java]

        categoryMealsViewModel.getMealByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealsLiveData().observe(this,{mealList->
            binding.tvCategoryCount.text = mealList.size.toString()
            categoryMealsAdapter.setMealsList(mealList)

        })
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }
}