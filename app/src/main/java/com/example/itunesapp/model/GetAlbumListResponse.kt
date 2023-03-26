package com.example.itunesapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetAlbumListResponse(
    @Json(name = "resultCount")
    val resultCount: Long?,
    @Json(name = "results")
    val results: List<Album>?
)