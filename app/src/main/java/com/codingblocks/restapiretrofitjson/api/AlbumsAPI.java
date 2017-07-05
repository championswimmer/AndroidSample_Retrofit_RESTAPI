package com.codingblocks.restapiretrofitjson.api;

import com.codingblocks.restapiretrofitjson.models.Album;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ITCONTROLLER on 7/5/2017.
 */

public interface AlbumsAPI {
    @GET("/albums")
    Call<ArrayList<Album>>getAlbums();

     interface PhotosAPI{
       @GET("/albums/{id}/photos")
         Call<ArrayList<Photo>> getPhotsOfAlbum(@Path("id") int albumId);
    }
}
