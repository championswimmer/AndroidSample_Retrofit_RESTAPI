package com.codingblocks.restapiretrofitjson.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.TodoAdapter;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.api.TodoService;
import com.codingblocks.restapiretrofitjson.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView rv;
    ArrayList<Todo> todoList=new ArrayList<>(0);
    TodoAdapter adapter=new TodoAdapter(todoList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        rv= (RecyclerView) findViewById(R.id.rv_todo);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration  decor=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(decor);

        TodoService todoService = Api.getInstance().getTodoService();


        if(getIntent().getExtras()==null){
            Call<ArrayList<Todo>> todos = todoService.getTodos();
            todos.enqueue(new Callback<ArrayList<Todo>>() {
                @Override
                public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                    todoList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

                }
            });
        }
        else{
            String userId=getIntent().getExtras().getString("userId");
            Call<ArrayList<Todo>> todos = todoService.getUserTodos(Integer.valueOf(userId));
            todos.enqueue(new Callback<ArrayList<Todo>>() {
                @Override
                public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                    todoList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {

                }
            });


        }
    }
}
