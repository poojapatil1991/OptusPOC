package com.example.optusdemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.optusdemo.R
import com.squareup.picasso.Picasso


/*Class to download images from server
Used Picasso library, it provides lazy loading and caching
 */
object PiccassoImageDownloader {
    @JvmStatic
    @BindingAdapter("android:src")
    fun downloadImage(img: ImageView, url: String?) {
        // Progress bar to show till image gets download
        val circularProgressDrawable = CircularProgressDrawable(OptusDemoApplication.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 20f
        circularProgressDrawable.start()
        if (url != null && url.isNotEmpty() && url != "") {
            Picasso.get()
                .load(url)
                .placeholder(circularProgressDrawable)
                .error(R.mipmap.ic_launcher)
                .into(img)
        } else {
            Picasso.get()
                .load(R.mipmap.ic_launcher)
                .into(img)
        }
    }

}