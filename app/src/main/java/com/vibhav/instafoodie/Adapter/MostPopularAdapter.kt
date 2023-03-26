package com.vibhav.instafoodie.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vibhav.instafoodie.databinding.PopularItemsBinding
import com.vibhav.instafoodie.pojo.MealsByCategory

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewholder>(){

    lateinit var onItemClick: ((MealsByCategory) -> Unit)
    private var mealList = ArrayList<MealsByCategory>()

    fun setMeals(mealList:ArrayList<MealsByCategory>){
        this.mealList = mealList
        notifyDataSetChanged()
    }

    class PopularMealViewholder(val binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewholder {
        return PopularMealViewholder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewholder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener{
            onItemClick.invoke(mealList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}