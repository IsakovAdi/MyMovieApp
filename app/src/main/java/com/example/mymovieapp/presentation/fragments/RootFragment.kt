package com.example.mymovieapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentRootBinding
import com.example.mymovieapp.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class RootFragment : Fragment() {
    private val binding:FragmentRootBinding by lazy {
        FragmentRootBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentList = arrayListOf(
            MoviesFragment(), SearchMoviesFragment(), ActorsFragment(), StorageMoviesFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.isSaveEnabled = false
        TabLayoutMediator(binding.tableLayout, binding.viewPager){ tab, position->
            when (position){
                0-> {
                    tab.setIcon(R.drawable.ic_movie)
                }
                1-> {
                    tab.setIcon(R.drawable.ic_search)
                }
                2-> {
                    tab.setIcon(R.drawable.ic_person)
                }
                3->{
                    tab.setIcon(R.drawable.ic_tv)
                }
            }
        }.attach()
        return binding.root
    }

}