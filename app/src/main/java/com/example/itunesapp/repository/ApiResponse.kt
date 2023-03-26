package com.example.itunesapp.repository

data class ApiResponse<T>(val status: Status, val data: T?, val error: Throwable?)

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}