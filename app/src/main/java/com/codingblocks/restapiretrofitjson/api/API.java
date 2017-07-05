package com.codingblocks.restapiretrofitjson.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by championswimmer on 30/06/17.
 */

public class API {

    private static API apiInstance;

    private PostsAPI postsAPI;
    private UsersAPI usersAPI;
    private CommentsAPI commentsAPI;
    private TodosAPI todosAPI;
    private AlbumsAPI albumsAPI;
    private AlbumsAPI.PhotosAPI albumPhotosAPI ;
    private PhotoAPI photoAPI ;

    public AlbumsAPI.PhotosAPI getAlbumPhotosAPI() {
        return albumPhotosAPI;
    }

    public PhotoAPI getPhotoAPI() {
        return photoAPI;
    }

    public PostsAPI getPostsAPI() {
        return postsAPI;
    }


    public UsersAPI getUsersAPI() {
        return usersAPI;
    }

    public CommentsAPI getCommentsAPI() {
        return commentsAPI;
    }

    public TodosAPI getTodosAPI() {
        return todosAPI;
    }

    public AlbumsAPI getAlbumsAPI() {
        return albumsAPI;
    }

    private API() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                        GsonConverterFactory.create()
                )
                .build();

        postsAPI = retrofit.create(PostsAPI.class);
        usersAPI = retrofit.create(UsersAPI.class);
        commentsAPI = retrofit.create(CommentsAPI.class);
        todosAPI = retrofit.create(TodosAPI.class);
        albumsAPI = retrofit.create((AlbumsAPI.class));
        albumPhotosAPI = retrofit.create(AlbumsAPI.PhotosAPI.class);
        photoAPI = retrofit.create(PhotoAPI.class);
    }

    public static API getInstance() {
        if (apiInstance == null) {
            apiInstance = new API();
        }

        return apiInstance;
    }
}
