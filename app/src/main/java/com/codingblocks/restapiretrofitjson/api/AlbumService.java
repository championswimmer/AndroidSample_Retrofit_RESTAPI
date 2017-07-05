package com.codingblocks.restapiretrofitjson.api;


import com.codingblocks.restapiretrofitjson.models.Album;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by amandhapola on 01/07/17.
 */

public interface AlbumService {
    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    @GET("/albums/{id}/photos")
    Call<ArrayList<Photo>> getPhotosByAlbumId(@Path("id") int id);

}
