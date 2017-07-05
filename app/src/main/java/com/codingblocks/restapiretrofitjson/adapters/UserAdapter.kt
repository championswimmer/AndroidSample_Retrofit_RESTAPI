package com.codingblocks.restapiretrofitjson.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.User
import kotlinx.android.synthetic.main.activity_user_list_item.view.*


/**
 * Created by amandhapola on 01/07/17.
 */
class UserAdapter(var userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){


    interface OnButtonClickedListener {
        fun OnUserPostButtonClickedListener(view:View , user: User)
        fun OnUserTodoButtonClickedListener(view:View , user:User)
    };

    lateinit var oupbcl: OnButtonClickedListener

    fun setOnuserPostButtonClickedListener(oupbcl: OnButtonClickedListener){
        this.oupbcl=oupbcl
    }
    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        var user:User=userList.get(position)
        if (holder != null) {
            holder.name.setText(user.name)
            holder.email.setText(user.email)
            holder.phone.setText(user.phone)
            holder.website.setText(user.website)
            oupbcl.OnUserPostButtonClickedListener(holder.view, user)
            oupbcl.OnUserTodoButtonClickedListener(holder.view,user)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {

        var v:View = LayoutInflater.from(parent?.context).inflate(R.layout.activity_user_list_item,parent,false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name=itemView.tv_name
        val email=itemView.tv_email
        val phone=itemView.tv_phone
        val website=itemView.tv_website
        val btn_posts=itemView.btn_userpost
        val btn_todo=itemView.btn_usertodos
        val view=itemView
    }
}