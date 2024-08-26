package com.example.moviebox.model.publicDataclasses

import com.google.gson.annotations.SerializedName

data class ProductionCountryListModel(
    @SerializedName("iso_31661")
    val iso31661: String? = null,
    val name: String? = null
)
