package com.codingblocks.restapiretrofitjson.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codingblocks.restapiretrofitjson.R;
import com.codingblocks.restapiretrofitjson.adapters.AlbumAdapter;
import com.codingblocks.restapiretrofitjson.api.AlbumService;
import com.codingblocks.restapiretrofitjson.api.Api;
import com.codingblocks.restapiretrofitjson.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class AlbumActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Album> albumList=new ArrayList<>(0);
    AlbumAdapter adapter = new AlbumAdapter(albumList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        rv= (RecyclerView) findViewById(R.id.rv_album);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));


        adapter.setOnAlbumClickListener(new AlbumAdapter.OnAlbumClickListener() {
            @Override
            public void onAlbumClicked(View view, final Album album) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AlbumActivity.this,PhotoActivity.class);
                        intent.putExtra("albumId",String.valueOf(album.getId()));
                        startActivity(intent);
                    }
                });
            }
        });


        AlbumService albumService = Api.getInstance().getAlbumService();
        Call<ArrayList<Album>> albums = albumService.getAlbums();
        albums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                albumList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
    }
}
