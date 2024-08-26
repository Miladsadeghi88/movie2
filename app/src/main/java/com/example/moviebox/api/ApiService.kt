package com.example.moviebox.api

import com.example.moviebox.model.detailMovie.DetailMovieModel
import com.example.moviebox.model.detailSeries.DetailSeries
import com.example.moviebox.model.popularMovies.PopularMoviesModel
import com.example.moviebox.model.popularSeries.PopularSeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //popular movies in main activity

    @GET("movie/popular")
    fun popularMoviesInMain(): Call<PopularMoviesModel>

    //popular series in main activity
    @GET("tv/popular")
    fun popularSeriesInMain(): Call<PopularSeriesModel>

    //detail of movie
    @GET("movie/{movie_id}")
    fun detailMovie(@Path("movie_id") id: Int): Call<DetailMovieModel>

    @GET("tv/{series_id}")
    fun detailSeries(@Path("series_id") id:Int) : Call<DetailSeries>
}
