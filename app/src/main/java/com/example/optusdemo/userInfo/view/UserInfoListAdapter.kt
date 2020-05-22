package com.example.optusdemo.userInfo.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optusdemo.R
import com.example.optusdemo.albumList.view.AlbumListActivity
import com.example.optusdemo.databinding.UserInfoBinding
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import com.example.optusdemo.utils.OptusDemoApplication
import java.util.ArrayList

class UserInfoListAdapter(private var mUserInfoViewModellList: ArrayList<UserInfoViewModel>?) :
    RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val userInBinding: UserInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_info_card, viewGroup, false)

        return ViewHolder(userInBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val userInfoViewModel = mUserInfoViewModellList!![position]
        viewHolder.bind(userInfoViewModel)
        viewHolder.itemView.setOnClickListener(viewHolder)
    }

    // View holder representing single row in list
    class ViewHolder(private val userInfoBinding: UserInfoBinding) :
        RecyclerView.ViewHolder(userInfoBinding.root), View.OnClickListener {

        fun bind(userInViewModel: UserInfoViewModel) {
            this.userInfoBinding.userInfoViewModel = userInViewModel
            userInfoBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            var albumListActivityIntent: Intent =
                Intent(OptusDemoApplication.context, AlbumListActivity::class.java)
            albumListActivityIntent.putExtra("ALBUM_ID", userInfoBinding.userInfoViewModel!!.id)
            OptusDemoApplication.context.startActivity(albumListActivityIntent)
        }
    }

    fun setArrayList(arrayList: ArrayList<UserInfoViewModel>) {
        mUserInfoViewModellList = arrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mUserInfoViewModellList!!.size
    }
}