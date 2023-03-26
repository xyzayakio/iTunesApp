package com.example.itunesapp.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesapp.databinding.ActivityMainBinding
import com.example.itunesapp.favourite.view.FavouriteActivity
import com.example.itunesapp.main.adapter.MainAdapter
import com.example.itunesapp.main.viewmodel.MainViewModel
import com.example.itunesapp.model.Album
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val mainAdapter: MainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fab.setOnClickListener {
                startActivity(Intent(this@MainActivity, FavouriteActivity::class.java))
            }

            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = mainAdapter
            mainAdapter.onItemClickListener = object : MainAdapter.OnItemClickListener {
                override fun onAlbumClick(album: Album) {

                }

                override fun onAddFavourite(album: Album) {
                    mainViewModel.addFavouriteAlbum(album)
                }

                override fun onRemoveFavourite(album: Album) {
                    mainViewModel.removeFavouriteAlbum(album)
                }
            }
        }

        lifecycleScope.launch {
            mainViewModel.albumDao.getAll().observe(this@MainActivity) { favouriteAlbums ->
                mainViewModel.favouriteAlbums =  favouriteAlbums
                val albums = mainViewModel.getAlbumListResponse.value?.data?.results ?: listOf()
                returnAlbumsWithFavourite(albums)
                mainAdapter.submitList(albums)
            }
        }

        mainViewModel.getAlbumListResponse.observe(this) {
            val albums = it.data?.results ?: listOf()
            returnAlbumsWithFavourite(albums)
            mainAdapter.submitList(albums)
        }
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.getAlbums()
    }

    private fun returnAlbumsWithFavourite(albums: List<Album>): List<Album> {
        albums.forEach { album ->
            album.isFavourite = mainViewModel.favouriteAlbums.contains(album)
        }
        return albums
    }
}