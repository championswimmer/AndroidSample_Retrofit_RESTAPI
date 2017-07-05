package com.codingblocks.restapiretrofitjson.api
import com.codingblocks.restapiretrofitjson.models.Photo;

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

/**
 * Created by ITCONTROLLER on 7/5/2017.
 */
public interface PhotoAPI {

    @GET("/photos/{id}")
    fun getPhoto(@Path("id") photoId:Int):Call<Photo>
}