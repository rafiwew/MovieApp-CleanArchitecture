package com.piwew.movieapp_cleanarchitecture.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.movieapp_cleanarchitecture.core.ui.MovieAdapter
import com.piwew.movieapp_cleanarchitecture.databinding.ActivityFavoriteBinding
import com.piwew.movieapp_cleanarchitecture.detail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val movieAdapter = MovieAdapter()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivActionBack.setOnClickListener { onSupportNavigateUp() }
        setupRecycleView()
        observeFavoriteMovieData()
    }

    private fun observeFavoriteMovieData() {
        favoriteViewModel.favoriteMovie.observe(this) { favoriteItem ->
            movieAdapter.submitList(favoriteItem)
            binding.viewEmpty.root.visibility = if (favoriteItem.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun setupRecycleView() {
        movieAdapter.onItemClick = { selectedItem ->
            startActivity(
                Intent(this, MovieDetailActivity::class.java)
                    .apply { putExtra(MovieDetailActivity.EXTRA_DATA, selectedItem) }
            )
        }

        with(binding.rvFavoriteMovie) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(false)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

}