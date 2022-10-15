package com.example.mymovieapp.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mymovieapp.databinding.ActivityMainBinding
import com.example.mymovieapp.presentation.viewModels.MovieFragmentViewModel
import com.example.mymovieapp.presentation.viewModels.MovieType
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}