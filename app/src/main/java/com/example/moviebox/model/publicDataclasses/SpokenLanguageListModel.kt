package com.example.moviebox.model.publicDataclasses

import com.google.gson.annotations.SerializedName

data class SpokenLanguageListModel(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639")
    val iso6391: String? = null,
    val name: String? = null
)
