//package com.example.mymovieapp.presentation.ui.activities
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mymovieapp.R
//import com.example.mymovieapp.data.cloud.Utils.POSTER_PATH_URL
//import com.example.mymovieapp.databinding.ActivityPersonDetailsBinding
//import com.example.mymovieapp.domain.models.movie.MovieDomain
//import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
//import com.example.mymovieapp.domain.models.person.PersonDomain
//import com.example.mymovieapp.presentation.ui.adapters.MovieItemAdapter
//import com.example.mymovieapp.presentation.ui.viewModels.PersonDetailsViewModel
//import com.squareup.picasso.Picasso
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class PersonDetailsActivity : AppCompatActivity(), MovieItemAdapter.RvClickListener {
//    private val viewModel by viewModel<PersonDetailsViewModel>()
//
//    private val binding: ActivityPersonDetailsBinding by lazy {
//        ActivityPersonDetailsBinding.inflate(layoutInflater)
//    }
//
//    private val moviesAdapter: MovieItemAdapter by lazy {
//        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE, this)
//    }
//
//    private lateinit var personNamesAdapter: ArrayAdapter<String>
//
//    private var person: PersonDomain? = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        parseIntent()
//        viewModel.getPerson(person!!.id)
//        parsePageDetails()
//        observePerson()
//        getPersonMovies()
//    }
//
//
//    private fun getPersonMovies() {
//        moviesAdapter.submitList(person!!.known_for)
//        binding.personMoviesRv.adapter = moviesAdapter
//    }
//
//    private fun parseToolbar(title: String) {
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = title
//    }
//
//    private fun initNamesAdapter(names: List<String>) {
//        personNamesAdapter = ArrayAdapter(
//            this@PersonDetailsActivity, R.layout.spinner_custom_item, names
//        )
//        binding.namesList.adapter = personNamesAdapter
//    }
//
//    private fun observePerson() {
//        viewModel.person.observe(this@PersonDetailsActivity) { person ->
//            parseToolbar(person.name)
//            Picasso.get().load(POSTER_PATH_URL + person.profile_path).into(binding.personImage)
//            binding.apply {
//                profession.text = person.known_for_department
//                biography.text = person.biography
//                birthday.text = person.birthday
//                deathDay.text = person.deathDay
//                personGender.text = person.gender
//                personName.text = person.name
//                personPopularity.text = person.popularity.toString()
//                birthPlace.text = person.place_of_birth
//            }
//            initNamesAdapter(person.also_known_as)
//        }
//    }
//
//    private fun parsePageDetails() {
//        val details = viewModel.detailsState
//        binding.apply {
//            professionText.text = details.known_for_department
//            namesText.text = details.also_known_as
//            biographyText.text = details.biography
//            birthdayText.text = details.birthday
//            deathDayText.text = details.deathDay
//            genderText.text = details.gender
//            personNameText.text = details.name
//            popularityText.text = details.rating
//            personMoviesText.text = details.known_for
//            birthPlaceText.text = details.place_of_birth
//        }
//    }
//
//    private fun parseIntent() {
//        person = intent.getParcelableExtra(PERSON)
//    }
//
//    companion object {
//        fun launchPersonDetailsActivity(context: Context, person: PersonDomain): Intent {
//            val intent = Intent(context, PersonDetailsActivity::class.java)
//            intent.putExtra(PERSON, person)
//            return intent
//        }
//
//        fun launchPersonDetailsActivity(context: Context, person: PersonDetailsDomain): Intent {
//            val intent = Intent(context, PersonDetailsActivity::class.java)
//            intent.putExtra(PERSON, person)
//            return intent
//        }
//
//        private const val PERSON = "person"
//    }
//
//    override fun onItemClick(movie: MovieDomain) {
//        startActivity(MovieDetailsActivity.launchMovieDetailsActivity(this, movie))
//    }
//
//    override fun onLongClick(movie: MovieDomain) {
//        viewModel.saveMovie(movie)
//    }
//}