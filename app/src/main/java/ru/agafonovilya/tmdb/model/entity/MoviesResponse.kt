package ru.agafonovilya.tmdb.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse (
    @Expose val page : Int,
    @Expose val results : List<Movie>,
    @Expose val total_pages : Int,
    @Expose val total_results : Int
): Parcelable