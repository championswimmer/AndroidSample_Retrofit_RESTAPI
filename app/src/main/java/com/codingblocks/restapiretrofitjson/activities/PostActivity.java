package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.PostAdapter;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.api.PostService;
import com.codingblocks.restapiretrofitjson.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Post> postList=new ArrayList<Post>(0);
    PostAdapter adapter=new PostAdapter(postList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ActionBar actionBar =getSupportActionBar();

        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView= (RecyclerView) findViewById(R.id.rv_post);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(dividerItemDecoration);


        adapter.setOnPostCLickListener(new PostAdapter.onPostClickListener() {
            @Override
            public void OnPostCLicked(View view, final Post post) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(PostActivity.this,CommentActivity.class);
                        intent.putExtra("postId",String.valueOf(post.getId()));
                        startActivity(intent);
                    }
                });
            }
        });



        PostService postService =  Api.getInstance().getPostService();

        if(getIntent().getExtras()==null){
            /////////
            actionBar.setTitle("Posts");

            Call<ArrayList<Post>>posts=postService.getAllPosts();
            posts.enqueue(new Callback<ArrayList<Post>>() {
                @Override
                public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                    postList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

                }
            });
            /////////
        }

        else{
            ////////////////////

            String userId=getIntent().getExtras().getString("userId");
            actionBar.setTitle("Posts for userID "+userId);
            Call<ArrayList<Post>> posts=postService.getPostOfUser(Integer.valueOf(userId));
            posts.enqueue(new Callback<ArrayList<Post>>() {
                @Override
                public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                    postList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

                }
            });
        }
    }
}
