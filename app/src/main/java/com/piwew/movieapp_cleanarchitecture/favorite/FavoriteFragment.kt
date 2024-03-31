package com.piwew.movieapp_cleanarchitecture.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.movieapp_cleanarchitecture.core.ui.MovieAdapter
import com.piwew.movieapp_cleanarchitecture.core.ui.ViewModelFactory
import com.piwew.movieapp_cleanarchitecture.databinding.FragmentFavoriteBinding
import com.piwew.movieapp_cleanarchitecture.detail.MovieDetailActivity

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val movieAdapter = MovieAdapter()
    private val favoriteViewModel by viewModels<FavoriteViewModel> { ViewModelFactory.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        observeFavoriteMovieData()
    }

    private fun observeFavoriteMovieData() {
        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner) { favoriteItem ->
            movieAdapter.submitList(favoriteItem)
            binding.viewEmpty.root.visibility = if (favoriteItem.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setupRecycleView() {
        movieAdapter.onItemClick = { selectedItem ->
            startActivity(
                Intent(activity, MovieDetailActivity::class.java)
                .apply { putExtra(MovieDetailActivity.EXTRA_DATA, selectedItem) }
            )
        }

        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}