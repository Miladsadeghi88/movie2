package com.example.moviebox.model.detailSeries

import com.example.moviebox.model.publicDataclasses.GenresListModel
import com.example.moviebox.model.publicDataclasses.ProductionCompaniesListModel
import com.example.moviebox.model.publicDataclasses.SpokenLanguageListModel
import com.google.gson.annotations.SerializedName

data class DetailSeries(
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("created_by")
    val createdBy: List<CreatedByListModel>,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    val genres: List<GenresListModel>,
    val homepage: String,
    val id: Int,

    @SerializedName("in_production")
    val inProduction: Boolean,

    val languages: List<String>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAirListModel?,

    val name: String,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any?, // Replace Any with the specific type if known

    val networks: List<NetworksListModel>,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesListModel>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCompaniesListModel>,

    val seasons: List<SeasionsListModel>,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageListModel>,

    val status: String,
    val tagline: String?,
    val type: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)
