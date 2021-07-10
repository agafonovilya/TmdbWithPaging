package ru.agafonovilya.tmdb.view

import ru.agafonovilya.tmdb.model.entity.Movie
import ru.agafonovilya.tmdb.view.fragment.DetailsFragment
import ru.agafonovilya.tmdb.view.fragment.MoviesFragment

sealed class Screens {
    class MoviesScreen(){
        fun getFragment() = MoviesFragment.newInstance()
    }

    class DetailsScreen(private val movie: Movie) {
        fun getFragment() = DetailsFragment.newInstance(movie)
    }
}