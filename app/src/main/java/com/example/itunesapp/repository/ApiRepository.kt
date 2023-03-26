package com.example.itunesapp.repository

import com.example.itunesapp.model.GetAlbumListResponse
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.awaitResponse

class ApiRepository(private val apiService: ApiService) {

    suspend fun getAlbumList(): ApiResponse<GetAlbumListResponse> = apiService.getAlbumList().handleApiResponse()

    private suspend inline fun <reified T> Call<T>.handleApiResponse(): ApiResponse<T> {
        return try {
            val response = this.awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                ApiResponse(Status.SUCCESS, data, null)
            } else {
                val error = HttpException(response)
                ApiResponse(Status.ERROR, null, error)
            }
        } catch (e: Exception) {
            ApiResponse(Status.ERROR, null, e)
        }
    }
}