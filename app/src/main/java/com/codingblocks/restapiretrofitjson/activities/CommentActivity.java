package com.codingblocks.restapiretrofitjson.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.CommentAdapter;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.api.PostService;
import com.codingblocks.restapiretrofitjson.models.Comment;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Comment> commentList=new ArrayList<>(0);
    CommentAdapter adapter=new CommentAdapter(commentList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ActionBar actionBar =getSupportActionBar();
        recyclerView= (RecyclerView) findViewById(R.id.rv_comment);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        String postId=getIntent().getExtras().getString("postId");
        actionBar.setTitle("Comments for " + postId );
        PostService postService= Api.getInstance().getPostService();
        Call<ArrayList<Comment>> comments = postService.getPostComments(Integer.valueOf(postId));

        comments.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                commentList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });

    }
}
