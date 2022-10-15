package com.example.mymovieapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentActorsBinding
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.adapters.PersonItemAdapter
import com.example.mymovieapp.presentation.viewModels.PersonsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ActorsFragment : Fragment() {

    private val viewModel by viewModel<PersonsViewModel>()
    private val binding: FragmentActorsBinding by lazy {
        FragmentActorsBinding.inflate(layoutInflater)
    }
    private val personsAdapter: PersonItemAdapter by lazy {
        PersonItemAdapter(MovieItemAdapter.PORTRAIT_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPersons()
        setupPersonsRv()
        observeViewModel()
        setupClickers()
        binding.personSwipeRefresh.setOnRefreshListener {
            viewModel.getPersons()
        }
    }


    private fun observeViewModel() {
        viewModel.apply {
            error.observe(viewLifecycleOwner) {
                makeToast(it.message.toString())
                Log.d("errorMessage", it.message.toString())
            }
            responseState.observe(viewLifecycleOwner) {
                binding.apply {
                    prevPageText.text = it.previousPage.toString()
                    currentPageText.text = it.page.toString()
                    nextPageText.text = it.nextPage.toString()
                }
            }
        }
    }

    private fun setupClickers() {
        binding.apply {
            nextBtn.setOnClickListener {
                viewModel.nextPage()
                nextBtn.visibility = INVISIBLE
                binding.progressBarNext.visibility = VISIBLE
            }
            prevBtn.setOnClickListener {
                viewModel.previousPage()
                prevBtn.visibility = INVISIBLE
                binding.progressBarPrev.visibility = VISIBLE
            }
        }
    }

    private fun setupPersonsRv() {
        binding.personsRv.hasFixedSize()
        viewModel.persons.observe(viewLifecycleOwner) {
            personsAdapter.submitList(it.persons)
            binding.personSwipeRefresh.isRefreshing = false
            setupVisibilities()
        }
        binding.personsRv.adapter = personsAdapter
    }

    private fun setupVisibilities() {
        binding.apply {
            nextBtn.visibility = VISIBLE
            progressBarNext.visibility = INVISIBLE
            prevBtn.visibility = VISIBLE
            progressBarPrev.visibility = INVISIBLE
            prevPageText.visibility = VISIBLE
            currentPageText.visibility = VISIBLE
            nextPageText.visibility = VISIBLE
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}