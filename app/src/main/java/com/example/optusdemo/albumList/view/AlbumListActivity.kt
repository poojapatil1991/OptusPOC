package com.example.optusdemo.albumList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.optusdemo.R
import com.example.optusdemo.utils.OptusDemoApplication

class AlbumListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        var albumID = intent.getStringExtra("ALBUM_ID")
        Toast.makeText(OptusDemoApplication.context,albumID,Toast.LENGTH_SHORT).show()
    }
}
