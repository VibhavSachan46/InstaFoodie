package com.vibhav.instafoodie.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.vibhav.instafoodie.Adapter.CategoriesAdapter
import com.vibhav.instafoodie.Adapter.CategoryMealAdapter
import com.vibhav.instafoodie.R
import com.vibhav.instafoodie.activity.CategoryMealsActivity
import com.vibhav.instafoodie.activity.MainActivity
import com.vibhav.instafoodie.activity.MealActivity
import com.vibhav.instafoodie.databinding.FragmentCategoriesBinding
import com.vibhav.instafoodie.pojo.Meal
import com.vibhav.instafoodie.viewmodel.HomeviewModel


class CategoriesFragment : Fragment() {


    private lateinit var binding:FragmentCategoriesBinding
    private lateinit var categoriesAdapter:CategoriesAdapter
    private lateinit var viewModel:HomeviewModel
    private lateinit var categoriesMealAdapter: CategoryMealAdapter

    private lateinit var randomMeal: Meal

    companion object{
        const val MEAL_ID = "com.vibhav.instafoodie.fragment.idMeal"
        const val MEAL_NAME = "com.vibhav.instafoodie.fragment.nameMeal"
        const val MEAL_THUMB = "com.vibhav.instafoodie.fragment.thumbMeal"
        const val CATEGORY_NAME = "com.vibhav.instafoodie.fragment.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        observeCategories()

        onCategoryclick()

//        onCategoryItemClicked()
    }

    private fun observeCategories() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer {categories->
            categoriesAdapter.setCategoryList(categories)

        })
    }

    private fun prepareRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }


    private fun onCategoryclick() {
        categoriesAdapter.onItemClick ={category ->

            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(HomeFragment.CATEGORY_NAME, category.strCategory)
            startActivity(intent)

        }
    }

//    private fun onCategoryItemClicked() {
//        categoriesAdapter.onItemClick = {meal->
//            val intent = Intent(activity,MealActivity::class.java)
//            intent.putExtra(HomeFragment.MEAL_ID, randomMeal.idMeal)
//            intent.putExtra(HomeFragment.MEAL_NAME, randomMeal.strMeal)
//            intent.putExtra(HomeFragment.MEAL_THUMB, randomMeal.strMealThumb)
//            startActivity(intent)
//        }
//    }

}