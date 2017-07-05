package com.codingblocks.restapiretrofitjson.api;


import com.codingblocks.restapiretrofitjson.models.Comment;
import com.codingblocks.restapiretrofitjson.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by amandhapola on 01/07/17.
 */

public interface PostService {
    @GET("/posts")
    Call<ArrayList<Post>> getAllPosts();

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") int id);

    @GET("/posts/{id}/comments")
    Call<ArrayList<Comment>> getPostComments(@Path("id") int id);

    @GET("/posts")
    Call<ArrayList<Post>> getPostOfUser(@Query("userId") int userId);
}
