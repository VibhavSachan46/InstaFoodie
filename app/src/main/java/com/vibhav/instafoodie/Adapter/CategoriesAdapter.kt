package com.vibhav.instafoodie.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vibhav.instafoodie.databinding.CategoryItemBinding
import com.vibhav.instafoodie.databinding.PopularItemsBinding
import com.vibhav.instafoodie.pojo.Category
import com.vibhav.instafoodie.pojo.CategoryList

class CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(val binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    private var CategoriesList = ArrayList<Category>()
    var onItemClick: ((Category)->Unit)? = null

    fun setCategoryList(CategoriesList:List<Category>){
        this.CategoriesList = CategoriesList as ArrayList<Category>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView).load(CategoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)
        holder.binding.tvCategoryName.text = CategoriesList[position].strCategory

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(CategoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return CategoriesList.size
    }
}