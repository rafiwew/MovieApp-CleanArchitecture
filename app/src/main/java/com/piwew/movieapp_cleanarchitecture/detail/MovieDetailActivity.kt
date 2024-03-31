package com.piwew.movieapp_cleanarchitecture.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.piwew.movieapp_cleanarchitecture.R
import com.piwew.movieapp_cleanarchitecture.core.domain.model.Movie
import com.piwew.movieapp_cleanarchitecture.core.utils.loadImage
import com.piwew.movieapp_cleanarchitecture.databinding.ActivityMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivActionBack.setOnClickListener { onSupportNavigateUp() }
        showDetailMovie(intent.getParcelableExtra(EXTRA_DATA))
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        with(binding) {
            detailMovie?.let {
                supportActionBar?.title = detailMovie.title
                ivItemPoster.loadImage("https://image.tmdb.org/t/p/original${detailMovie.posterPath}")
                tvItemTitle.text = detailMovie.title
                customTitle.text = detailMovie.title
                tvItemReleaseDate.text = detailMovie.releaseDate
                tvItemLanguage.text = detailMovie.originalLanguage
                tvItemOverview.text = detailMovie.overview
                tvItemRating.text = detailMovie.voteAverage.toString()
                rbItemRating.rating = detailMovie.voteAverage.toFloat()

                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)
                fabItemFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    movieDetailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.fabItemFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this@MovieDetailActivity,
                if (statusFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "DATA"
    }
}