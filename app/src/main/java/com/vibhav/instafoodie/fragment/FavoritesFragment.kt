package com.vibhav.instafoodie.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.vibhav.instafoodie.Adapter.FavouritesMealsAdapter
import com.vibhav.instafoodie.R
import com.vibhav.instafoodie.activity.MainActivity
import com.vibhav.instafoodie.databinding.FragmentFavoritesBinding
import com.vibhav.instafoodie.viewmodel.HomeviewModel


class FavoritesFragment : Fragment() {
    private lateinit var binding:FragmentFavoritesBinding
    private lateinit var viewModel:HomeviewModel
    private lateinit var favouriteAdapter: FavouritesMealsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeFavourites()
    }

    private fun prepareRecyclerView() {
        favouriteAdapter = FavouritesMealsAdapter()
        binding.rvFavourites.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = favouriteAdapter
        }
    }

    private fun observeFavourites() {
        viewModel.observeFavouritesMealsLiveData().observe(requireActivity(), Observer { meals ->
            favouriteAdapter.differ.submitList(meals)
        })

    }

}