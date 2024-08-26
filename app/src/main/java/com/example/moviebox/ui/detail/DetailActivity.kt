package com.example.moviebox.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.moviebox.R
import com.example.moviebox.databinding.ActivityDetailBinding
import com.example.moviebox.utils.BASE_URL_IMAGE
import com.example.moviebox.utils.FROM_MOVIE
import com.example.moviebox.utils.FROM_SERIES
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailViewModel by viewModels()
    private var from = -1
    private var id = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        bundle?.let {
            from = bundle.getInt("from_detail")
            id = bundle.getInt("id")
        }


        if (from == FROM_MOVIE) {
            viewModel.getDetailMovie(id)
            viewModel.detailMovie.observe(this) { movie ->
                movie?.let {
                    binding.txtMovieName.text = movie.title
                    binding.txtMovieRate.text = movie.voteAverage.toString()
                    binding.txtMovieTime.text = "${movie.runtime} min"
                    binding.txtMovieDate.text = movie.releaseDate
                    binding.txtMovieSummaryInfo.text = movie.overview

                    Picasso.get()
                        .load(BASE_URL_IMAGE + movie.posterPath)
                        .into(binding.imgNormalPoster)
                    Picasso.get()
                        .load(BASE_URL_IMAGE + movie.backdropPath)
                        .into(binding.imgBgPoster)
                }
            }
        } else if (from == FROM_SERIES) {
            viewModel.getDetailSeries(id)
            viewModel.detailSeries.observe(this) { series ->
                series?.let {
                    binding.txtMovieName.text = series.name
                    binding.txtMovieRate.text = series.voteAverage.toString()
                    binding.txtMovieTime.text = "${series.episodeRunTime?.average()?.toInt()} min"
                    binding.txtMovieDate.text = series.firstAirDate
                    binding.txtMovieSummaryInfo.text = series.overview

                    Picasso.get()
                        .load(BASE_URL_IMAGE + series.posterPath)
                        .into(binding.imgNormalPoster)
                    Picasso.get()
                        .load(BASE_URL_IMAGE + series.backdropPath)
                        .into(binding.imgBgPoster)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}