package com.codingblocks.restapiretrofitjson.adapters
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.Album
import kotlinx.android.synthetic.main.activity_album_list_item.view.*

/**
 * Created by amandhapola on 01/07/17.
 */
class AlbumAdapter(var albumList : ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    interface OnAlbumClickListener{
        fun onAlbumClicked(view : View, album: Album)
    }
    lateinit var oacl : OnAlbumClickListener
    fun setOnAlbumClickListener(oacl:OnAlbumClickListener){
        this.oacl=oacl
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumViewHolder {
        var v = LayoutInflater.from(parent?.context).inflate(R.layout.activity_album_list_item,parent,false);
        return AlbumViewHolder(v)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder?, position: Int) {
        var album = albumList.get(position)
        if(holder!=null){
            holder.tv_album.setText(album.title)
            oacl.onAlbumClicked(holder.view,album)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    class AlbumViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tv_album=itemView.tv_album
        var view =itemView
    }
}