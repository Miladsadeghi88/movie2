package com.example.moviebox.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviebox.databinding.ActivityMainBinding
import com.example.moviebox.ui.detail.DetailActivity
import com.example.moviebox.ui.main.adapter.Adapter
import com.example.moviebox.ui.main.adapter.RecyclerViewType
import com.example.moviebox.ui.main.moviewAdapter.MoviesAdapter
import com.example.moviebox.ui.main.seriesAdapater.SeriesAdapter
import com.example.moviebox.utils.FROM_MOVIE
import com.example.moviebox.utils.FROM_SERIES

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var adapterMovies: Adapter? = null
    private var adapterSeries: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularSeries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        viewModel.getPopularMovies()
        viewModel.movies.observe(this) {

            adapterMovies =
                Adapter(movies = it, series = null, RecyclerViewType.fromMovie, onClick = {id->
                    startDetail(id, FROM_MOVIE)
                })
            binding.rvPopularMovies.adapter = adapterMovies
            binding.loading1.isVisible = false
            viewModel.getPopularSeries()
        }
        viewModel.series.observe(this) {
            adapterSeries =
                Adapter(movies = null, series = it, RecyclerViewType.fromSeries, onClick = {id->
                    startDetail(id, FROM_SERIES)
                })
            binding.rvPopularSeries.adapter = adapterSeries
            binding.loading2.isVisible = false

        }
    }

    private fun startDetail(id:Int,from:Int){
        val bundle = Bundle().apply {
            putInt("from_detail", from)
            putInt("id", id)
        }
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)    }
}