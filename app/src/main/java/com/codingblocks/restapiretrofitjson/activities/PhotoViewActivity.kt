package com.codingblocks.restapiretrofitjson.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.api.API
import com.codingblocks.restapiretrofitjson.models.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo_view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class PhotoViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        var photoId :Int = getIntent().getIntExtra("photoId",0)

        API.getInstance().photoAPI.getPhoto(photoId).enqueue(object: retrofit2.Callback<Photo?> {
            override fun onFailure(call: Call<Photo?>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Photo?>?, response: Response<Photo?>?) {
                Picasso.with(this@PhotoViewActivity).load(response!!.body()!!.url).into(photoView_imageView)
                photoview_Title.text = response!!.body()!!.title
            }
        })
    }
}
