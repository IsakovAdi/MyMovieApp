package com.example.mymovieapp.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.data.network.Utils.POSTER_PATH_URL
import com.example.mymovieapp.databinding.ActivityPersonDetailsBinding
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonModel
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.viewModels.PersonDetailsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<PersonDetailsViewModel>()

    private val binding: ActivityPersonDetailsBinding by lazy {
        ActivityPersonDetailsBinding.inflate(layoutInflater)
    }

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE)
    }

    private lateinit var personNamesAdapter: ArrayAdapter<String>

    private var person: PersonModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseIntent()
        viewModel.getPerson(person!!.id)
        parsePageDetails()
        observePerson()
        getPersonMovies()
    }

    private fun setupMoviesAdapterClickListener() {
        moviesAdapter.onMovieItemClickListener = {
            val intent =
                MovieDetailsActivity.launchMovieDetailsActivity(this@PersonDetailsActivity, it)
            startActivity(intent)
        }
        moviesAdapter.onMovieItemLongClickListener = {
            viewModel.saveMovie(it)
        }
    }

    private fun getPersonMovies() {
        moviesAdapter.submitList(person!!.known_for)
        binding.personMoviesRv.adapter = moviesAdapter
        setupMoviesAdapterClickListener()
    }

    private fun parseToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun initNamesAdapter(names: List<String>) {
        personNamesAdapter = ArrayAdapter(
            this@PersonDetailsActivity, R.layout.spinner_custom_item, names
        )
        binding.namesList.adapter = personNamesAdapter
    }

    private fun observePerson() {
        viewModel.person.observe(this@PersonDetailsActivity) {  person->
            parseToolbar(person.name)
            Picasso.get().load(POSTER_PATH_URL + person.profile_path).into(binding.personImage)
            binding.apply {
                profession.text = person.known_for_department
                biography.text = person.biography
                birthday.text = person.birthday
                deathDay.text = person.deathDay
                personGender.text = person.gender
                personName.text = person.name
                personPopularity.text = person.popularity.toString()
                birthPlace.text = person.place_of_birth
            }
            initNamesAdapter(person.also_known_as)
        }
    }

    private fun parsePageDetails() {
        val details = viewModel.detailsState
        binding.apply {
            professionText.text = details.known_for_department
            namesText.text = details.also_known_as
            biographyText.text = details.biography
            birthdayText.text = details.birthday
            deathDayText.text = details.deathDay
            genderText.text = details.gender
            personNameText.text = details.name
            popularityText.text = details.rating
            personMoviesText.text = details.known_for
            birthPlaceText.text = details.place_of_birth
        }
    }

    private fun parseIntent() {
        person = intent.getParcelableExtra(PERSON)
    }

    companion object {
        fun launchPersonDetailsActivity(context: Context, person: PersonModel): Intent {
            val intent = Intent(context, PersonDetailsActivity::class.java)
            intent.putExtra(PERSON, person)
            return intent
        }

        fun launchPersonDetailsActivity(context: Context, person: PersonDetailsModel): Intent {
            val intent = Intent(context, PersonDetailsActivity::class.java)
            intent.putExtra(PERSON, person)
            return intent
        }

        private const val PERSON = "person"
    }
}