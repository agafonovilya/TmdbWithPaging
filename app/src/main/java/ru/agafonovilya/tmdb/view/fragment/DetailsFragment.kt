package ru.agafonovilya.tmdb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.agafonovilya.tmdb.Injection
import ru.agafonovilya.tmdb.databinding.DetailsFragmentBinding
import ru.agafonovilya.tmdb.model.entity.Movie
import ru.agafonovilya.tmdb.viewModel.DetailsViewModel

class DetailsFragment: Fragment() {
    companion object {
        fun newInstance(movie: Movie) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", movie)
            }
        }
    }

    private lateinit var viewModel: DetailsViewModel
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(DetailsViewModel::class.java)

        movie = arguments?.getParcelable<Movie>("movie") as Movie

        binding.detailsTitle.text = movie.title
        binding.detailsAbout.text = movie.overview
        Injection.provideImageLoader().loadInto(movie.backdrop_path, binding.detailsBackgroundImage)
        Injection.provideImageLoader().loadWithRoundCornersInto(movie.poster_path, binding.detailsPoster)
    }

}