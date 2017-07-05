package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.UserAdapter;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.api.UserService;
import com.codingblocks.restapiretrofitjson.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    public static final String TAG="response";
    private  ArrayList<User> userList=new ArrayList<>(0);
    RecyclerView recyclerView=null;
    UserAdapter adapter=new UserAdapter(userList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        recyclerView= (RecyclerView) findViewById(R.id.rv_user);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserService userService= Api.getInstance().getUserService();
        Call<ArrayList<User>> users = userService.getUsers();
        Log.d(TAG, "onCreate: "+ users);

        //TODO rxjava
        adapter.setOnuserPostButtonClickedListener(new UserAdapter.OnButtonClickedListener() {
            @Override
            public void OnUserTodoButtonClickedListener(View view, final User user) {
                Button b=(Button)view.findViewById(R.id.btn_usertodos);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserActivity.this,TodoActivity.class);
                        intent.putExtra("userId",String.valueOf(user.getId()).toString());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void OnUserPostButtonClickedListener(View v, final User user) {
                Button b= (Button) v.findViewById(R.id.btn_userpost);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(UserActivity.this,PostActivity.class);
                        intent.putExtra("userId", String.valueOf(user.getId()));
                        startActivity(intent);
                    }
                });

            }
        });
        /////////////////

        users.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                Log.d(TAG, "onResponse: "+response.body().size());
                userList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: here" + t.toString());
            }
        });


    }
}
