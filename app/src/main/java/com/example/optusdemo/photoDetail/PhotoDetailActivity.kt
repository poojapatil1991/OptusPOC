package com.example.optusdemo.photoDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.optusdemo.R
import com.example.optusdemo.utils.PiccassoImageDownloader
import kotlinx.android.synthetic.main.activity_photo_detail.*

/*
*PhotoDetailActivity to show the selected photo from the album
* albumID : album id from the AlbumListActivity
* photoID : photo id of selected photo
* photoUrl : url of selected photo
* photoTitle : title of selected photo
 */

class PhotoDetailActivity : AppCompatActivity() {
    private lateinit var albumID: String
    private lateinit var photoID: String
    private lateinit var photoUrl: String
    private lateinit var photoTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        albumID = intent.getStringExtra("ALBUM_ID")
        photoID = intent.getStringExtra("PHOTO_ID")
        photoUrl = intent.getStringExtra("PHOTO_URL")
        photoTitle = intent.getStringExtra("PHOTO_TITLE")


        tv_album_id.text = "Album Id: " + albumID
        tv_photo_id.text = "Photo Id: " + photoID
        PiccassoImageDownloader.downloadImage(iv_photo, photoUrl)
        tv_photo_title.text = photoTitle
    }
}
