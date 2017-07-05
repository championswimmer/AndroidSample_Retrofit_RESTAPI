package com.codingblocks.restapiretrofitjson.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.codingblocks.restapiretrofitjson.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_big_photo.*

class BigPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_big_photo)
        val url = intent.extras.getString("imgUrl")
        val title = intent.extras.getString("title")
        Picasso.with(this).load(url).into(img_bigImage)
        tv_bigtitle.setText(title)

    }
}
