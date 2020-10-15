package com.fromitt.stackoverflowtestapp.network

import com.google.gson.annotations.SerializedName

/*
* @author Tkachov Vasyl
* @since 13.10.2020
*/
data class WikipediaImage(
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("width")
    var width: Int? = 0,
    @SerializedName("height")
    var height: Int? = 0
)