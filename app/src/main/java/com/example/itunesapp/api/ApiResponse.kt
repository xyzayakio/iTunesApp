package com.example.itunesapp.api

data class ApiResponse<T>(val status: Status, val data: T? = null, val error: Throwable? = null)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}