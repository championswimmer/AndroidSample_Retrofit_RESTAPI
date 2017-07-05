package com.codingblocks.restapiretrofitjson.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.Post

import kotlinx.android.synthetic.main.activity_post_list_item.view.*

/**
 * Created by amandhapola on 01/07/17.
 */
class PostAdapter(var postList:ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface onPostClickListener{
        fun OnPostCLicked(view: View,post: Post);
    }

    lateinit var opcl: onPostClickListener

    fun setOnPostCLickListener(opcl:onPostClickListener){
        this.opcl=opcl
    }
    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {
        var post: Post = postList.get(position)
        if (holder != null) {
            holder.title.setText(post.title)
            holder.body.setText(post.body)
            opcl.OnPostCLicked(holder.view,post)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder {
        var v:View = LayoutInflater.from(parent?.context).inflate(R.layout.activity_post_list_item,parent,false)
        return PostViewHolder(v)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title =itemView.tv_title
        var body=itemView.tv_body
        var view=itemView
    }
}