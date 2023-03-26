package com.example.itunesapp.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.database.AlbumDao
import com.example.itunesapp.databinding.ItemAlbumBinding
import com.example.itunesapp.model.Album
import javax.inject.Inject

class MainAdapter: ListAdapter<Album, RecyclerView.ViewHolder>(AlbumDiffCallback()) {

    @Inject
    lateinit var albumDao: AlbumDao

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = getItem(position)
        when (holder) {
            is MainViewHolder -> {
                holder.bind(album, onItemClickListener)
            }
        }
    }

    class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.collectionId == newItem.collectionId
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {

        fun onAlbumClick(album: Album)

        fun onAddFavourite(album: Album)

        fun onRemoveFavourite(album: Album)
    }

    class MainViewHolder(
        private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album, onItemClickListener: OnItemClickListener?) {
            binding.album = album

            itemView.setOnClickListener {
                onItemClickListener?.onAlbumClick(album)
            }
            binding.checkBox.setOnCheckedChangeListener { _, boolean ->
                if (boolean) {
                    onItemClickListener?.onAddFavourite(album)
                } else {
                    onItemClickListener?.onRemoveFavourite(album)
                }
            }

            binding.executePendingBindings()
        }
    }
}