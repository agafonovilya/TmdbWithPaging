package ru.agafonovilya.tmdb.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountries(
    @Expose val iso3166_1: String,
    @Expose val name: String
): Parcelable
