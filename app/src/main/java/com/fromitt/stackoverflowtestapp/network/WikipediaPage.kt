package com.fromitt.stackoverflowtestapp.network

import com.google.gson.annotations.SerializedName

/*
* @author Tkachov Vasyl
* @since 13.10.2020
*/
data class WikipediaPage(
    @SerializedName("pageid")
    var pageid: Int? = null,
    @SerializedName("ns")
    var ns: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: WikipediaImage? = null,
    @SerializedName("pageimage")
    var pageimage: String? = null
)