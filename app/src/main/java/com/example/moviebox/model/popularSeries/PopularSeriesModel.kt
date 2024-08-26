package com.example.moviebox.model.popularSeries

import com.google.gson.annotations.SerializedName

data class PopularSeriesModel(
    val page :Int,
    val results:List<ResultsSeriesListModel>,
    @SerializedName("total_pages")
    val totalPages:Int,
    @SerializedName("total_results")
    val totalResults:Int
)
