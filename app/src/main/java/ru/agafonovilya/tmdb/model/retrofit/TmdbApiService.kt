package ru.agafonovilya.tmdb.model.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.agafonovilya.tmdb.model.entity.MoviesResponse

interface TmdbApiService {
    @GET("3/discover/movie")
    suspend fun getMoviesAsync(
        @Query("api_key") apiKey: String = "274f828ad283bd634ef4fc1ee4af255f",
        @Query("page") page: Int
    ): MoviesResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"

        fun create(): TmdbApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbApiService::class.java)
        }
    }
}


