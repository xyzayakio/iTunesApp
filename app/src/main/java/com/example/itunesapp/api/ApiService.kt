package com.example.itunesapp.api

import com.example.itunesapp.model.GetAlbumListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getAlbumList(
        @Query("term") term: String = "jack+johnson",
        @Query("entity") entity: String = "album"
    ): Call<GetAlbumListResponse>
}