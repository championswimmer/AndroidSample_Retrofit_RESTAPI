package com.codingblocks.restapiretrofitjson.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener
import com.codingblocks.restapiretrofitjson.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_photo.view.*

/**
 * Created by ITCONTROLLER on 7/5/2017.
 */
class PhotoAdapter(val context: Context, var photoArrayList: ArrayList<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) {
        holder?.bindView(photoArrayList[position])
    }
    fun updatePhotos (pList:ArrayList<Photo>):Unit{
        this.photoArrayList = pList
        notifyDataSetChanged()
    }

    internal var onItemClickListener :OnItemClickListener? = null

    fun setOnItemClickListener(opcl:OnItemClickListener){
        this.onItemClickListener = opcl
    }

    override fun getItemCount(): Int = photoArrayList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoViewHolder {
        var view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.list_item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(photo: Photo): Unit {
            Picasso.with(itemView.context).load(photo.url).into(itemView.photo_imageView)
            itemView.photo_tvTitle.text = photo.title
            itemView.setOnClickListener { onItemClickListener!!.onItemClick(photo.id,itemView) }
        }
    }
}