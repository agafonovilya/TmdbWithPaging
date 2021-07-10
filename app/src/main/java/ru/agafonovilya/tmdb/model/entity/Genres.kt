package ru.agafonovilya.tmdb.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genres (
    @Expose val id : Int,
    @Expose val name : String
): Parcelable