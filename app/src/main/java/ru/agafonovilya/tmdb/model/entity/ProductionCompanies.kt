package ru.agafonovilya.tmdb.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompanies(
    @Expose val name: String,
    @Expose val id: Long,
    @Expose val logoPath: String? = null,
    @Expose val originCountry: String
) : Parcelable
