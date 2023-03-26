package com.example.itunesapp.favourite.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesapp.databinding.ActivityFavouriteBinding
import com.example.itunesapp.favourite.viewmodel.FavouriteViewModel
import com.example.itunesapp.main.adapter.MainAdapter
import com.example.itunesapp.model.Album
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteActivity : FragmentActivity() {

    private val favouriteViewModel: FavouriteViewModel by viewModels()

    private val mainAdapter: MainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toolbar.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            recyclerView.layoutManager = LinearLayoutManager(this@FavouriteActivity)
            recyclerView.adapter = mainAdapter
            mainAdapter.onItemClickListener = object : MainAdapter.OnItemClickListener {
                override fun onAlbumClick(album: Album) {

                }

                override fun onAddFavourite(album: Album) {
                    favouriteViewModel.addFavouriteAlbum(album)
                }

                override fun onRemoveFavourite(album: Album) {
                    favouriteViewModel.removeFavouriteAlbum(album)
                }
            }
        }

        lifecycleScope.launch {
            favouriteViewModel.albumDao.getAll().observe(this@FavouriteActivity) { favouriteAlbums ->
                favouriteAlbums.forEach { it.isFavourite = true }
                favouriteViewModel.favouriteAlbums =  favouriteAlbums
                mainAdapter.submitList(favouriteAlbums)
            }
        }
    }
}