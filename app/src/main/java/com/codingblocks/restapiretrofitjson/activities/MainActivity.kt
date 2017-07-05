package com.codingblocks.restapiretrofitjson.activities
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.activities.AlbumActivity
import com.codingblocks.restapiretrofitjson.activities.PostActivity
import com.codingblocks.restapiretrofitjson.activities.TodoActivity
import com.codingblocks.restapiretrofitjson.activities.UserActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_photos.setOnClickListener({view -> startActivity(Intent(this@MainActivity, AlbumActivity::class.java)); });
        btn_posts.setOnClickListener({view -> startActivity(Intent(this@MainActivity, PostActivity::class.java)); });
        btn_todos.setOnClickListener({view -> startActivity(Intent(this@MainActivity, TodoActivity::class.java)); });
        btn_users.setOnClickListener({view -> startActivity(Intent(this@MainActivity, UserActivity::class.java)); });
    }
}
