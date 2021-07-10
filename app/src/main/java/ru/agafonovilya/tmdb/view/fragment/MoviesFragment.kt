package ru.agafonovilya.tmdb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ru.agafonovilya.tmdb.Injection
import ru.agafonovilya.tmdb.R
import ru.agafonovilya.tmdb.databinding.MoviesFragmentBinding
import ru.agafonovilya.tmdb.model.entity.Movie
import ru.agafonovilya.tmdb.view.Screens
import ru.agafonovilya.tmdb.view.adapter.MoviesAdapter
import ru.agafonovilya.tmdb.viewModel.MoviesViewModel

class MoviesFragment : Fragment() {
    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MoviesAdapter(openDetails())

    private var requestJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory(this)
        ).get(MoviesViewModel::class.java)

        binding.moviesRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.moviesRecyclerview.adapter = adapter

        request()
        initRequest()
    }

    private fun request() {
        // Make sure we cancel the previous job before creating a new one
        requestJob?.cancel()
        requestJob = lifecycleScope.launch {
            viewModel.moviesRepo().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initRequest() {
        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.moviesRecyclerview.scrollToPosition(0) }
        }
    }

    private fun openDetails(): (Movie) -> Unit = {
        replaceFragment(it)
    }

    private fun replaceFragment(it: Movie) {
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack("backStack")
            .replace(R.id.container, Screens.DetailsScreen(it).getFragment()).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}