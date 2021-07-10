package ru.agafonovilya.tmdb

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.agafonovilya.tmdb.model.retrofit.TmdbApiService
import ru.agafonovilya.tmdb.repository.TmdbRepository
import ru.agafonovilya.tmdb.util.GlideImageLoader
import ru.agafonovilya.tmdb.view.fragment.DetailsFragment
import ru.agafonovilya.tmdb.view.fragment.MoviesFragment
import ru.agafonovilya.tmdb.viewModel.DetailsViewModelFactory
import ru.agafonovilya.tmdb.viewModel.MoviesViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [TmdbRepository] based on the [TmdbApiService]
     */
    private fun provideTmdbRepository(): TmdbRepository {
        return TmdbRepository(TmdbApiService.create())
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(fragment: Fragment): ViewModelProvider.Factory =
        when(fragment::class.java) {
            MoviesFragment::class.java -> MoviesViewModelFactory(provideTmdbRepository())
            DetailsFragment::class.java -> DetailsViewModelFactory()
            else -> ViewModelProvider.NewInstanceFactory()
        }

    /**
     * Base URL for TMDB API image provider
     */
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    /**
     * Provides the image loader.
     */
    fun provideImageLoader() = GlideImageLoader(IMAGE_BASE_URL)
}
