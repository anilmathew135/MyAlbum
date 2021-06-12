package com.example.myalbum.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbum.R
import com.example.myalbum.model.response.Album
import com.example.myalbum.utils.Utils
import kotlinx.android.synthetic.main.row_album_layout.view.*

class AlbumsListAdapter(var albumsList: List<Album>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Album) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_album_layout,
            parent,
            false
        )
        return AlbumsListHolderView(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = albumsList[position]
        val viewHolder: AlbumsListHolderView = holder as AlbumsListHolderView
        viewHolder.albumTitleTv.text = album?.title?:""
        viewHolder.albumIdTv.text = "ID: " + album?.id?.toString()?:""
        Utils.loadImageFromDrawable(holder.getItemView().getContext(),
            R.drawable.defaultimg,
            R.drawable.error_placeholder,
            R.drawable.error_placeholder,
            viewHolder.albumImageView)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    /**
     * Update Albums list on recycler view.
     *
     * @param albums List of Album objects to update.
     * @return void
     */
    fun updateAlbumsList(albums: List<Album>) {
        albumsList = albums
        notifyDataSetChanged()
    }

    private inner class AlbumsListHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumImageView: ImageView = itemView.albumImageView
        var albumTitleTv: TextView = itemView.albumTitleTv
        var albumIdTv: TextView = itemView.albumIdTv
        private val view: View = itemView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(albumsList[adapterPosition])
            }
        }

        fun getItemView(): View {
            return this.view
        }
    }
}