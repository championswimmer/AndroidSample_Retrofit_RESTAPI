package com.codingblocks.restapiretrofitjson.api;


import com.codingblocks.restapiretrofitjson.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amandhapola on 01/07/17.
 */

public interface TodoService {
    @GET("/todos")
    Call<ArrayList<Todo>> getTodos();

    @GET("/todos")
    Call<ArrayList<Todo>> getUserTodos(@Query("userId") int userId);
}