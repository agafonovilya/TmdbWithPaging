package ru.agafonovilya.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.agafonovilya.tmdb.Injection
import ru.agafonovilya.tmdb.R
import ru.agafonovilya.tmdb.databinding.ItemMovieBinding
import ru.agafonovilya.tmdb.model.entity.Movie

class MoviesViewHolder(view: View, val onItemClickListener: (Movie) -> Unit) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)
    private var movie: Movie? = null
    private val imageLoader = Injection.provideImageLoader()

    fun bind(movie: Movie?) {
        if (movie == null) {
            binding.itemMoviePoster.setImageResource(R.drawable.ic_baseline_refresh_24)
        } else {
            showMovieData(movie)
            itemView.setOnClickListener { onItemClickListener(movie) }
        }
    }

    private fun showMovieData(movie: Movie) {
        this.movie = movie

        binding.itemMovieTitle.text = movie.title
        imageLoader.loadInto(movie.poster_path, binding.itemMoviePoster)

    }

    companion object {
        fun create(parent: ViewGroup, onItemClickListener: (Movie) -> Unit): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
            return MoviesViewHolder(view, onItemClickListener)
        }
    }
}
