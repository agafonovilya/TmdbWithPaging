package ru.agafonovilya.tmdb.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.agafonovilya.tmdb.model.entity.Movie
import ru.agafonovilya.tmdb.repository.TmdbRepository

class MoviesViewModel(private val repository: TmdbRepository) : ViewModel() {
    private var currentMoviesResponse: Flow<PagingData<Movie>>? = null

    fun moviesRepo(): Flow<PagingData<Movie>> {
        val lastResult = currentMoviesResponse
        if (lastResult != null) {
            return lastResult
        }

        val newResult: Flow<PagingData<Movie>> =
            repository.getMoviesStream().cachedIn(viewModelScope)
        currentMoviesResponse = newResult
        return newResult
    }
}

class MoviesViewModelFactory(private val repository: TmdbRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
