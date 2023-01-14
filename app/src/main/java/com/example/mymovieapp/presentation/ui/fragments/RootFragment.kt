package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentRootBinding
import com.example.mymovieapp.presentation.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class RootFragment : Fragment() {
    private val binding: FragmentRootBinding by lazy {
        FragmentRootBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        TabLayoutMediator(binding.tableLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_movie)

                1 -> tab.setIcon(R.drawable.ic_search)

                2 -> tab.setIcon(R.drawable.ic_person)

                3 -> tab.setIcon(R.drawable.ic_tv)

            }
        }.attach()
        return binding.root
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val adapter = VPagerAdapter(requireActivity().supportFragmentManager, fragments = fragments)
//
//        binding.navView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_movies -> {
//                    binding.viewPager.currentItem = 0
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.nav_search -> {
//                    binding.viewPager.currentItem = 1
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.nav_person -> {
//                    binding.viewPager.currentItem = 2
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.nav_storage -> {
//                    binding.viewPager.currentItem = 3
//                    return@setOnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }
//
//        binding.viewPager.adapter = adapter
//
//    }
}