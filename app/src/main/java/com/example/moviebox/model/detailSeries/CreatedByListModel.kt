package com.example.moviebox.model.detailSeries

import com.google.gson.annotations.SerializedName

data class CreatedByListModel(
    val id: Int?=null,
    val creditId: String?=null,
    val name: String?=null,
    @SerializedName("original_name")
    val originalName: String?=null,
    val gender: Int?=null,
    @SerializedName("profile_path")
    val profilePath: String?=null
)
