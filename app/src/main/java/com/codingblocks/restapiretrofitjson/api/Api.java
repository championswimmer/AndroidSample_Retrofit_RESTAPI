package com.codingblocks.restapiretrofitjson.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amandhapola on 01/07/17.
 */

public class Api {
    private static final Api ourInstance = new Api();
    private  AlbumService albumService;
    private PostService postService;
    private TodoService todoService;
    private UserService userService;

    public AlbumService getAlbumService() {
        return albumService;
    }

    public PostService getPostService() {
        return postService;
    }

    public TodoService getTodoService() {
        return todoService;
    }

    public UserService getUserService() {
        return userService;
    }

    public static final String BASE_URL="https://jsonplaceholder.typicode.com";
    public static Api getInstance() {
        return ourInstance;
    }

    private Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        albumService = retrofit.create(AlbumService.class);
        postService = retrofit.create(PostService.class);
        todoService=retrofit.create(TodoService.class);
        userService=retrofit.create(UserService.class);
    }
}
