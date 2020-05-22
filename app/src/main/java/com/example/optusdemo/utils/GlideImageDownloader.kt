package com.example.optusdemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.optusdemo.R

/*Class to download images from server
Used Glide library, it provides lazy loading and caching
 */
object GlideImageDownloader {
    @JvmStatic
    @BindingAdapter("android:src")
    fun downloadImage(img: ImageView, url: String?) {
        // Progress bar to show till image gets download
        val circularProgressDrawable = CircularProgressDrawable(OptusDemoApplication.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 20f
        circularProgressDrawable.start()

        if (url != null && url.isNotEmpty() && url != "") {
            Glide.with(OptusDemoApplication.context).load(url).error(R.mipmap.ic_launcher)
                .placeholder(circularProgressDrawable).into(img)
        } else {
            Glide.with(OptusDemoApplication.context).load(R.mipmap.ic_launcher)
                .placeholder(circularProgressDrawable).into(img)
        }
    }
}