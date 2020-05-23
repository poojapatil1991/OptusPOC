package com.example.optusdemo.albumList.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optusdemo.R
import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.databinding.AlbumDetailsBinding
import com.example.optusdemo.photoDetail.PhotoDetailActivity
import com.example.optusdemo.utils.OptusDemoApplication
import java.util.*


class AlbumListAdapter(private var mAlbumDetailsViewModellList: ArrayList<AlbumDetailViewModel>?) :
    RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val albumDetailsBinding: AlbumDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.album_details_card, parent, false)

        return ViewHolder(albumDetailsBinding)
    }

    override fun getItemCount(): Int {
        return mAlbumDetailsViewModellList!!.size
    }

    override fun onBindViewHolder(holder: AlbumListAdapter.ViewHolder, position: Int) {
        val albumDetailsViewModel = mAlbumDetailsViewModellList!![position]
        holder.bind(albumDetailsViewModel)
        holder.itemView.setOnClickListener(holder)
        setFadeAnimation(holder.itemView)
    }

    // View holder representing single row in list
    class ViewHolder(private val albumDetailsBinding: AlbumDetailsBinding) :
        RecyclerView.ViewHolder(albumDetailsBinding.root), View.OnClickListener {

        fun bind(albumDetailsViewModel: AlbumDetailViewModel) {
            this.albumDetailsBinding.albumDetailsViewModel = albumDetailsViewModel
            albumDetailsBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            var photoDetailActivityIntent: Intent =
                Intent(OptusDemoApplication.context, PhotoDetailActivity::class.java)
            photoDetailActivityIntent.putExtra("ALBUM_ID", albumDetailsBinding.albumDetailsViewModel!!.albumId)
            photoDetailActivityIntent.putExtra("PHOTO_ID", albumDetailsBinding.albumDetailsViewModel!!.id)
            photoDetailActivityIntent.putExtra("PHOTO_URL", albumDetailsBinding.albumDetailsViewModel!!.url)
            photoDetailActivityIntent.putExtra("PHOTO_TITLE", albumDetailsBinding.albumDetailsViewModel!!.title)

            OptusDemoApplication.context.startActivity(photoDetailActivityIntent)
        }
    }

    fun setArrayList(arrayList: ArrayList<AlbumDetailViewModel>) {
        mAlbumDetailsViewModellList = arrayList
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

}