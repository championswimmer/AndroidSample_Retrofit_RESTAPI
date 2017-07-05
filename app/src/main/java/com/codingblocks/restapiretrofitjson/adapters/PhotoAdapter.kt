package com.codingblocks.restapiretrofitjson.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.Photo

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_list_item.view.*

/**
 * Created by amandhapola on 01/07/17.
 */
class PhotoAdapter(var photoList:ArrayList<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    interface OnPhotoClickListener{
        fun OnPhotoClicked(view:View,photo: Photo)
    }
    lateinit var opcl : OnPhotoClickListener

    fun setOnPhotoClickListener(opcl : OnPhotoClickListener){
        this.opcl=opcl
    }

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) {
        var photo = photoList.get(position)
        if(holder!=null)
        {
            Picasso.with(holder.view.context).load(photo.thumbnailUrl).placeholder(R.drawable.placeholder).into(holder.imgview)
            holder.tv_album_title.setText(photo.title)
            opcl.OnPhotoClicked(holder.view,photo)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoViewHolder {
        var v = LayoutInflater.from(parent?.context).inflate(R.layout.activity_photo_list_item,parent,false)
        return PhotoViewHolder(v)
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imgview=itemView.img_album_thumbnail
        var tv_album_title=itemView.tv_album_title
        var view =itemView

    }
}