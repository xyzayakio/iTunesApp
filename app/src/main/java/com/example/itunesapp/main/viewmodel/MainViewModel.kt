package com.example.itunesapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.model.GetAlbumListResponse
import com.example.itunesapp.api.ApiRepository
import com.example.itunesapp.api.ApiResponse
import com.example.itunesapp.api.Status
import com.example.itunesapp.database.AlbumDao
import com.example.itunesapp.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    val albumDao: AlbumDao
) : ViewModel() {

    private val _getAlbumListResponse = MutableLiveData<ApiResponse<GetAlbumListResponse>>()
    val getAlbumListResponse: LiveData<ApiResponse<GetAlbumListResponse>>
        get() = _getAlbumListResponse

    var favouriteAlbums: List<Album> = listOf()

    fun addFavouriteAlbum(album: Album) {
        viewModelScope.launch {
            albumDao.insert(album)
        }
    }

    fun removeFavouriteAlbum(album: Album) {
        viewModelScope.launch {
            albumDao.delete(album)
        }
    }

    fun getAlbums() {
        viewModelScope.launch {
            _getAlbumListResponse.postValue(ApiResponse(status = Status.LOADING))
            val result = apiRepository.getAlbumList()
            _getAlbumListResponse.postValue(result)
        }
    }
}