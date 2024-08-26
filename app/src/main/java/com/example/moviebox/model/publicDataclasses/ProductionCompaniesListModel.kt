package com.example.moviebox.model.publicDataclasses

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesListModel(
    val id: Int?=null,
    @SerializedName("logo_path")
    val logoPath: String?=null,
    val name: String?=null,
    @SerializedName("origin_country")
    val originCountry: String?=null
)
