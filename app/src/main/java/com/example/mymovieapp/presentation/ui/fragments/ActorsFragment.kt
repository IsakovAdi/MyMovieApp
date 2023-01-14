package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.databinding.FragmentActorsBinding
import com.example.mymovieapp.presentation.models.person.PersonUi
import com.example.mymovieapp.presentation.ui.adapters.PersonItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.RvClickListener
import com.example.mymovieapp.presentation.ui.viewModels.PersonsViewModel
import com.example.mymovieapp.presentation.ui.viewModels.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ActorsFragment : Fragment(), RvClickListener<PersonUi> {

    private val viewModel by viewModels<PersonsViewModel>()
    private val binding: FragmentActorsBinding by lazy {
        FragmentActorsBinding.inflate(layoutInflater)
    }
    private val personsAdapter: PersonItemAdapter by lazy {
        PersonItemAdapter(this@ActorsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiAndClickers()
        observe()
    }

    private fun setupUiAndClickers() {
        binding.apply {
            personsRv.adapter = personsAdapter
            nextBtn.setOnClickListener {
                viewModel.nextPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnClickListener {
                viewModel.previousPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun observe() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest {
                personsAdapter.personsList = it.persons
                binding.pageConstraint.visibility = View.VISIBLE
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.personResponseState.collectLatest { state ->
                binding.apply {
                    prevPageText.text = state.previousPage.toString()
                    currentPageText.text = state.page.toString()
                    nextPageText.text = state.nextPage.toString()
                    prevBtn.apply {
                        isClickable = state.isHasPreviousPage
                        isFocusable = state.isHasPreviousPage
                    }
                    nextBtn.apply {
                        isClickable = state.isHasNextPage
                        isFocusable = state.isHasNextPage
                    }
                }
            }
        }
    }

    override fun onItemClick(item: PersonUi) = Unit

    override fun onLongClick(item: PersonUi) = Unit
}