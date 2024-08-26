package com.example.moviebox.ui.main.moviewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebox.R
import com.example.moviebox.databinding.ViewHolderItemFilmBinding
import com.example.moviebox.model.popularMovies.PopularMoviesModel
import com.example.moviebox.model.popularMovies.ResultsMovieListModel
import com.example.moviebox.utils.BASE_URL_IMAGE
import com.squareup.picasso.Picasso

class MoviesAdapter(
    val popularMoviesAdapter: PopularMoviesModel,
   val onClick :(movie: ResultsMovieListModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.PopularMoviesViewHolder>() {
    inner class PopularMoviesViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        val binding = ViewHolderItemFilmBinding.bind(itemsView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
return PopularMoviesViewHolder(
LayoutInflater.from(parent.context).inflate(
R.layout.view_holder_item_film,parent,false)
)


    }

    override fun getItemCount(): Int = popularMoviesAdapter.results.size

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = popularMoviesAdapter.results[position]
        holder.binding.apply {
            txtTitle.text = movie.title
            txtScore.text = movie.voteAverage.toString()
            Picasso.get()
                .load(BASE_URL_IMAGE + movie.posterPath)
                .into(pic)
            root.setOnClickListener {
                onClick.invoke(movie)

            }



        }
    }
}