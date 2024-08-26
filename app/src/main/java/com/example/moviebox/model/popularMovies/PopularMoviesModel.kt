package com.example.moviebox.model.popularMovies

import com.google.gson.annotations.SerializedName


data class PopularMoviesModel(

    val page :Int,
    val results:List<ResultsMovieListModel>,
    @SerializedName("total_pages")
    val totalPages:Int,
    @SerializedName("total_results")
    val totalResults:Int
)
