package com.piwew.movieapp_cleanarchitecture.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.movieapp_cleanarchitecture.R
import com.piwew.movieapp_cleanarchitecture.core.data.Resource
import com.piwew.movieapp_cleanarchitecture.core.ui.MovieAdapter
import com.piwew.movieapp_cleanarchitecture.core.ui.ViewModelFactory
import com.piwew.movieapp_cleanarchitecture.databinding.FragmentHomeBinding
import com.piwew.movieapp_cleanarchitecture.detail.MovieDetailActivity
import com.piwew.movieapp_cleanarchitecture.detail.MovieDetailActivity.Companion.EXTRA_DATA

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val movieAdapter = MovieAdapter()
    private val homeViewModel by viewModels<HomeViewModel> { ViewModelFactory.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        observeMovieData()
    }

    private fun setupRecycleView() {
        movieAdapter.onItemClick = { selectedItem ->
            startActivity(Intent(activity, MovieDetailActivity::class.java)
                .apply { putExtra(EXTRA_DATA, selectedItem) }
            )
        }

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun observeMovieData() {
        homeViewModel.movie.observe(requireActivity()) { result ->
            showLoading(result is Resource.Loading)
            when(result) {
                is Resource.Success -> movieAdapter.submitList(result.data)
                is Resource.Error -> showErrorMessage(result.message)
                else -> {}
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorMessage(errorMessage: String?) {
        with(binding.viewError) {
            root.visibility = View.VISIBLE
            tvError.text = errorMessage ?: getString(R.string.something_wrong)
        }
    }
}