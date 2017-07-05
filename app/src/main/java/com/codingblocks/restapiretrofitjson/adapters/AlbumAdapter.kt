package com.codingblocks.restapiretrofitjson.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener
import com.codingblocks.restapiretrofitjson.models.Album
import kotlinx.android.synthetic.main.list_item_album.view.*

/**
 * Created by ITCONTROLLER on 7/5/2017.
 */

public class AlbumAdapter(val context : Context, var albumArrayList : ArrayList<Album>):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

    internal var onItemClickListener : OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AlbumViewHolder {
        var ItemView :View = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_album,parent,false)
        return AlbumViewHolder(ItemView)
    }
    fun setOnItemClickListener( oacl : OnItemClickListener):Unit{
        this.onItemClickListener = oacl ;
    }
    fun updateAlbum( list : ArrayList<Album>){
        this.albumArrayList = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = albumArrayList.size

    override fun onBindViewHolder(holder: AlbumViewHolder?, position: Int) {
        holder?.bindAlbum(albumArrayList[position])
    }

    inner class AlbumViewHolder(itemView : View?):RecyclerView.ViewHolder(itemView){
            fun bindAlbum(thisAlbum : Album):Unit {
                itemView.album_tvTitle.text = thisAlbum.title
                itemView.setOnClickListener { onItemClickListener?.onItemClick(thisAlbum.id,itemView) }
            }
    }
}