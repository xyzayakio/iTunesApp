package com.example.itunesapp.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunesapp.database.AlbumDao
import com.example.itunesapp.model.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    val albumDao: AlbumDao
) : ViewModel() {

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
}