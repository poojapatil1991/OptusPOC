package com.example.optusdemo.albumList.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optusdemo.R
import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.databinding.AlbumDetailsBinding
import com.example.optusdemo.userInfo.view.UserInfoListAdapter
import com.example.optusdemo.utils.OptusDemoApplication
import java.util.ArrayList

class AlbumListAdapter(private var mAlbumDetailsViewModellList: ArrayList<AlbumDetailViewModel>?) :
    RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val albumDetailsBinding: AlbumDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.album_details_card, parent, false)

        return ViewHolder(albumDetailsBinding)
    }

    override fun getItemCount(): Int {
        mAlbumDetailsViewModellList!!.size
    }

    override fun onBindViewHolder(holder: AlbumListAdapter.ViewHolder, position: Int) {
        val albumDetailsViewModel = mAlbumDetailsViewModellList!![position]
        holder.bind(albumDetailsViewModel)
        holder.itemView.setOnClickListener(holder)
    }

    // View holder representing single row in list
    class ViewHolder(private val albumDetailsBinding: AlbumDetailsBinding) :
        RecyclerView.ViewHolder(albumDetailsBinding.root), View.OnClickListener {

        fun bind(albumDetailsViewModel: AlbumDetailViewModel) {
            this.albumDetailsBinding.albumDetailsViewModel = albumDetailsViewModel
            albumDetailsBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            Toast.makeText(OptusDemoApplication.context, "Pooja", Toast.LENGTH_SHORT).show()
        }
    }

}