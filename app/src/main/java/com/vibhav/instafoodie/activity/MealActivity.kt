package com.vibhav.instafoodie.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vibhav.instafoodie.R
import com.vibhav.instafoodie.databinding.ActivityMealBinding
import com.vibhav.instafoodie.db.MealDatabase
import com.vibhav.instafoodie.fragment.HomeFragment
import com.vibhav.instafoodie.pojo.Meal
import com.vibhav.instafoodie.viewmodel.MealViewModel
import com.vibhav.instafoodie.viewmodel.MealViewModelFactory

class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding: ActivityMealBinding
    private lateinit var youtubeLink: String
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate((layoutInflater))
        setContentView(binding.root)

        val mealDatabase = MealDatabase.getInstance(this)
        val viewmodelFactory = MealViewModelFactory(mealDatabase)
        mealMvvm = ViewModelProvider(this,viewmodelFactory)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationinViews()

        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observedMealDetailLiveData()

        onYoutubeImageClick()
        onFavouriteClick()


    }

    private fun onFavouriteClick() {
        binding.btnFav.setOnClickListener {
            mealToSave?.let {
                 mealMvvm.insertMeal(it)
                Toast.makeText(this,"MEal Saved", Toast.LENGTH_SHORT).show()
        }
        }

    }

    private fun onYoutubeImageClick() {
        binding.iconYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private var mealToSave:Meal?=null
    private fun observedMealDetailLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this, object: Observer<Meal> {
            override fun onChanged(t: Meal?) {
                OnresponseCase()
                val meal = t
                mealToSave = meal
                binding.tvCategory.text = "Category : ${meal!!.strCategory}"
                binding.tvArea.text = "Country : ${meal!!.strArea}"
                binding.tvInstructionsSteps.text = meal!!.strInstructions

                youtubeLink = meal.strYoutube.toString()
            }
        })
    }

    private fun setInformationinViews(){
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imageMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent(){
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnFav.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.iconYoutube.visibility = View.INVISIBLE
    }

    private fun OnresponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnFav.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.iconYoutube.visibility = View.VISIBLE
    }
}