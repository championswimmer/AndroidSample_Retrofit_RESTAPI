package com.codingblocks.restapiretrofitjson.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.adapters.AlbumAdapter
import com.codingblocks.restapiretrofitjson.api.API
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener
import com.codingblocks.restapiretrofitjson.models.Album
import kotlinx.android.synthetic.main.activity_album.*

import retrofit2.Call
import retrofit2.Response


class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        activity_Title.text = "ALBUMS"

        var  albumAdapter : AlbumAdapter = AlbumAdapter(this, ArrayList<Album>())
        album_rv.layoutManager = LinearLayoutManager(this)
        albumAdapter.setOnItemClickListener(OnItemClickListener { itemId, view ->
            val i : Intent = Intent(this@AlbumActivity,PhotosActivity::class.java)
            i.putExtra("albumId",itemId)
            startActivity(i)
        })
        album_rv.adapter = albumAdapter
        API.getInstance().albumsAPI.albums.enqueue(object: retrofit2.Callback<java.util.ArrayList<Album>?> {
            override fun onFailure(call: Call<java.util.ArrayList<Album>?>?, t: Throwable?) {            }

            override fun onResponse(call: Call<java.util.ArrayList<Album>?>?, response: Response<java.util.ArrayList<Album>?>?) {
                albumAdapter.updateAlbum(response!!.body()!!)
            }
        })
    }
}
