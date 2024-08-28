package com.example.moviebox.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.R
import com.example.moviebox.databinding.ViewHolderItemFilmBinding
import com.example.moviebox.model.popularMovies.PopularMoviesModel
import com.example.moviebox.model.popularSeries.PopularSeriesModel
import com.example.moviebox.utils.BASE_URL_IMAGE
import com.example.moviebox.utils.FROM_MOVIE
import com.squareup.picasso.Picasso

class Adapter(
    val movies: PopularMoviesModel? = null,
    val series: PopularSeriesModel? = null,
    val from: RecyclerViewType,
    val onClick: (id: Int) -> Unit,

    ) : RecyclerView.Adapter<Adapter.ItemsViewHolders>() {

    inner class ItemsViewHolders(items: View) : RecyclerView.ViewHolder(items) {
        val binding = ViewHolderItemFilmBinding.bind(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolders {
        return ItemsViewHolders(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_item_film, parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return when (from) {
            RecyclerViewType.fromMovie -> movies?.results?.size ?: 0
            RecyclerViewType.fromSeries -> series?.results?.size ?: 0
        }


    }

    override fun onBindViewHolder(holder: ItemsViewHolders, position: Int) {
        when (from) {
            RecyclerViewType.fromMovie -> {

                movies?.let {
                    val movie = it.results[position]
                    holder.binding.apply {
                        txtTitle.text = movie.title
                        txtScore.text = movie.voteAverage.toString()
                        Picasso.get()
                            .load(BASE_URL_IMAGE + movie.posterPath)
                            .into(pic)
                        root.setOnClickListener {
                            onClick.invoke(movie.id ?: 0)

                        }
                    }
                }

            }

            RecyclerViewType.fromSeries -> {
                series?.let {
                    val serial = it.results[position]
                    holder.binding.apply {
                        txtTitle.text = serial.name
                        txtScore.text = serial.voteAverage.toString()
                        Picasso.get()
                            .load(BASE_URL_IMAGE + serial.posterPath)
                            .into(pic)

                        root.setOnClickListener {
                            onClick.invoke(serial.id ?: 0)
                        }
                    }
                }
            }
        }
    }
}
