package com.codingblocks.restapiretrofitjson.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.adapters.PhotoAdapter
import com.codingblocks.restapiretrofitjson.api.API
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener
import com.codingblocks.restapiretrofitjson.models.Photo
import kotlinx.android.synthetic.main.activity_photos.*
import kotlinx.android.synthetic.main.activity_photos.view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PhotosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        var albumID :Int = intent.getIntExtra("albumId",0)
        activity_Title.text = "Photos of Album " + albumID
        photo_rv.layoutManager = GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false)

        var photoAdapter : PhotoAdapter = PhotoAdapter(this@PhotosActivity,ArrayList<Photo>())
        photoAdapter.setOnItemClickListener(OnItemClickListener { itemId, view ->
            var i :Intent= Intent(this@PhotosActivity,PhotoViewActivity::class.java)
            i.putExtra("photoId",itemId)
            startActivity(i)
        })
        photo_rv.adapter = photoAdapter

        API.getInstance().albumPhotosAPI.getPhotsOfAlbum(albumID).enqueue(object: retrofit2.Callback<java.util.ArrayList<Photo>?> {
            override fun onResponse(call: Call<java.util.ArrayList<Photo>?>?, response: Response<java.util.ArrayList<Photo>?>?) {
                photoAdapter.updatePhotos(response!!.body()!!)
            }

            override fun onFailure(call: Call<java.util.ArrayList<Photo>?>?, t: Throwable?) {
            }
        })

    }
}
