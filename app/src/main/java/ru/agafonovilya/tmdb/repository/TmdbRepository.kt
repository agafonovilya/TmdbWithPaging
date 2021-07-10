package ru.agafonovilya.tmdb.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.agafonovilya.tmdb.data.TmdbPagingSource
import ru.agafonovilya.tmdb.model.entity.Movie
import ru.agafonovilya.tmdb.model.retrofit.TmdbApiService

class TmdbRepository(private val apiService: TmdbApiService) {

    //The stream of data that will emit every time we get more data from the network.
    fun getMoviesStream() : Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { TmdbPagingSource(apiService)}
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}