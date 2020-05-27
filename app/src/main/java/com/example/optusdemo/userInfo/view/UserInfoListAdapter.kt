package com.example.optusdemo.userInfo.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.optusdemo.R
import com.example.optusdemo.albumList.view.AlbumListActivity
import com.example.optusdemo.databinding.UserInfoBinding
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import com.example.optusdemo.utils.OptusDemoApplication
import java.util.*

/*
* UserInfoListAdapter adapter for the user ino list
* mUserInfoViewModelList : list of user data
 */

class UserInfoListAdapter(private var mUserInfoViewModelList: ArrayList<UserInfoViewModel>?) :
    RecyclerView.Adapter<UserInfoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val userInBinding: UserInfoBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_info_card, viewGroup, false)

        return ViewHolder(userInBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val userInfoViewModel = mUserInfoViewModelList!![position]
        viewHolder.bind(userInfoViewModel)
        viewHolder.itemView.setOnClickListener(viewHolder)
        setAnimation(viewHolder.itemView, position)
    }

    // View holder representing single row in list
    class ViewHolder(private val userInfoBinding: UserInfoBinding) :
        RecyclerView.ViewHolder(userInfoBinding.root), View.OnClickListener {

        fun bind(userInViewModel: UserInfoViewModel) {
            this.userInfoBinding.userInfoViewModel = userInViewModel
            userInfoBinding.executePendingBindings()
        }

        /*
        * Onclick listner for single item in recycler view
         */

        override fun onClick(v: View?) {
            val albumListActivityIntent =
                Intent(OptusDemoApplication.context, AlbumListActivity::class.java)
            albumListActivityIntent.putExtra("ALBUM_ID", userInfoBinding.userInfoViewModel!!.id)
            OptusDemoApplication.context.startActivity(albumListActivityIntent)
        }
    }


    fun setArrayList(arrayList: ArrayList<UserInfoViewModel>) {
        mUserInfoViewModelList = arrayList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mUserInfoViewModelList!!.size
    }

    protected var mLastPosition = -1

    /*
    * Animation for loading card.
     */

    protected fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > mLastPosition) {
            val anim = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            anim.duration =
                Random().nextInt(501).toLong() //to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim)
            mLastPosition = position
        }
    }
}