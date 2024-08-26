package com.example.moviebox.model.popularSeries

import com.google.gson.annotations.SerializedName

data class ResultsSeriesListModel(

val adult: Boolean?=null,
@SerializedName("backdrop_path")
val backdropPath: String?=null,
@SerializedName("genre_ids")
val genreIds: List<Int>?=null,
val id: Int,
@SerializedName("origin_country")
val originCountry: List<String>?=null,
@SerializedName("original_language")
val originalLanguage: String?=null,
@SerializedName("original_name")
val originalName: String?=null,
val overview: String?=null,
val popularity: Double?=null,
@SerializedName("poster_path")
val posterPath: String?=null,
@SerializedName("first_air_date")
val firstAirDate: String?=null,
val name: String?=null,
@SerializedName("vote_average")
val voteAverage: Double?=null,
@SerializedName("vote_count")
val voteCount: Int?=null
)

