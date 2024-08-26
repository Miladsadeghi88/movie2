package com.example.moviebox.model.detailMovie

import com.example.moviebox.model.publicDataclasses.GenresListModel
import com.example.moviebox.model.publicDataclasses.ProductionCompaniesListModel
import com.example.moviebox.model.publicDataclasses.ProductionCountryListModel
import com.example.moviebox.model.publicDataclasses.SpokenLanguageListModel
import com.google.gson.annotations.SerializedName

data class DetailMovieModel(
    val adult: Boolean?=null,
    @SerializedName("backdrop_path")
    val backdropPath: String?=null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any?=null,
    val budget: Int,
    val genres: List<GenresListModel>?=null,
    val homepage: String?=null,
    val id: Int?=null,
    @SerializedName("imdb_id")
    val imdbId: String?=null,
    @SerializedName("origin_country")
    val originCountry: List<String>?=null,
    @SerializedName("original_language")
    val originalLanguage: String?=null,
    @SerializedName("original_title")
    val originalTitle: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    @SerializedName("poster_path")
    val posterPath: String?=null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesListModel>?=null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryListModel>?=null,
    @SerializedName("release_date")
    val releaseDate: String?=null,
    val revenue: Int?=null,
    val runtime: Int?=null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageListModel>?=null,
    val status: String?=null,
    val tagline: String?=null,
    val title: String?=null,
    val video: Boolean?=null,
    @SerializedName("vote_average")
    val voteAverage: Double?=null,
    @SerializedName("vote_count")
    val voteCount: Int?=null

)
