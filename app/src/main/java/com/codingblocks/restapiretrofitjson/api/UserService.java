package com.codingblocks.restapiretrofitjson.api;


import com.codingblocks.restapiretrofitjson.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by amandhapola on 01/07/17.
 */

public interface UserService {
    @GET("/users")
    Call<ArrayList<User>> getUsers();


}
