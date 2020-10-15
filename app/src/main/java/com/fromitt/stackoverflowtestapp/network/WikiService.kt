package com.fromitt.stackoverflowtestapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
* @author Tkachov Vasyl
* @since 14.10.2020
*/
interface WikiService {
    @GET("/w/api.php?action=query&prop=pageimages&format=json&pithumbsize=250")
    suspend fun getWikiData(@Query("titles") band: String): Response<WikipediaResponse?>
}