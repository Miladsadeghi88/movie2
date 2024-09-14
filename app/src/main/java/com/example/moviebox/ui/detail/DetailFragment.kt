package com.example.moviebox.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviebox.databinding.FragmentDetailBinding
import com.example.moviebox.utils.BASE_URL_IMAGE
import com.example.moviebox.utils.FROM_MOVIE
import com.example.moviebox.utils.FROM_SERIES
import com.squareup.picasso.Picasso


class  DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private var from = -1
    private var id = -1
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            from = it.getInt("from_detail")
            id = it.getInt("id")
        }




        if (from == FROM_MOVIE) {
            viewModel.getDetailMovie(id)
            viewModel.detailMovie.observe(viewLifecycleOwner) { movie ->
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
            viewModel.detailSeries.observe(viewLifecycleOwner) { series ->
                series?.let {
                    binding.txtMovieName.text = series.name
                    binding.txtMovieRate.text = series.voteAverage.toString()
                    binding.txtMovieTime.text = "${series.episodeRunTime.average().toInt()} min"
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
    }


}