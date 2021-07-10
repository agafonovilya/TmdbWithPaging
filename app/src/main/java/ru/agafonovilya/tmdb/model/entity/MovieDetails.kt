package ru.agafonovilya.tmdb.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetails (
    @Expose val adult : Boolean,
    @Expose val backdrop_path : String,
    @Expose val belongs_to_collection : String,
    @Expose val budget : Int,
    @Expose val genres : List<Genres>,
    @Expose val homepage : String,
    @Expose val id : Int,
    @Expose val imdb_id : String,
    @Expose val original_language : String,
    @Expose val original_title : String,
    @Expose val overview : String,
    @Expose val popularity : Int,
    @Expose val poster_path : String,
    @Expose val production_companies : List<ProductionCompanies>,
    @Expose val production_countries : List<ProductionCountries>,
    @Expose val release_date : String,
    @Expose val revenue : Int,
    @Expose val runtime : Int,
    @Expose val spoken_languages : List<String>,
    @Expose val status : String,
    @Expose val tagline : String,
    @Expose val title : String,
    @Expose val video : Boolean,
    @Expose val vote_average : Int,
    @Expose val vote_count : Int
): Parcelable