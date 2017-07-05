package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.PhotoAdapter;
import com.codingblocks.restapiretrofitjson.api.AlbumService;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Photo> photoList=new ArrayList<>(0);
    PhotoAdapter adapter = new PhotoAdapter(photoList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        rv= (RecyclerView) findViewById(R.id.rv_photos);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        int albumId=Integer.valueOf(getIntent().getExtras().getString("albumId"));


        adapter.setOnPhotoClickListener(new PhotoAdapter.OnPhotoClickListener() {
            @Override
            public void OnPhotoClicked(View view, final Photo photo) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PhotoActivity.this,BigPhotoActivity.class);
                        intent.putExtra("imgUrl",photo.getUrl());
                        intent.putExtra("title",photo.getTitle());
                        startActivity(intent);
                    }
                });
            }
        });


        AlbumService albumService = Api.getInstance().getAlbumService();
        final Call<ArrayList<Photo>> photos = albumService.getPhotosByAlbumId(albumId);

        photos.enqueue(new Callback<ArrayList<Photo>>() {
            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                photoList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {

            }
        });

    }
}
