package com.codingblocks.restapiretrofitjson.adapters
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.Comment
import kotlinx.android.synthetic.main.activity_comment_list_item.view.*

/**
 * Created by amandhapola on 01/07/17.
 */
class CommentAdapter(var commentList : ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder?, position: Int) {
        var comment=commentList.get(position)
        if (holder != null) {
            holder.name.setText(comment.name)
            holder.email.setText(comment.email)
            holder.body.setText(comment.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentViewHolder {
        var v : View=LayoutInflater.from(parent?.context).inflate(R.layout.activity_comment_list_item,parent,false)
        return CommentViewHolder(v)
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name=itemView.tv_commentername
        var email=itemView.tv_commenteremail
        var body=itemView.tv_commentbody
        var v=itemView
    }
}