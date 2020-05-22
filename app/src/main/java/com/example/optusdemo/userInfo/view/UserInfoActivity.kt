package com.example.optusdemo.userInfo.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.optusdemo.R
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import com.example.optusdemo.utils.NetworkConnection
import com.example.optusdemo.utils.OptusDemoApplication
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.view.*
import kotlinx.android.synthetic.main.content_user_info.*

class UserInfoActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    LifecycleOwner {

    private var userInfoListAdapter: UserInfoListAdapter =
        UserInfoListAdapter(ArrayList<UserInfoViewModel>())
    private var context: UserInfoActivity? = null
    private var userInfoViewModel: UserInfoViewModel? = null
    private lateinit var mDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        setSupportActionBar(toolbar)
        toolbar.user_info_toolbar_title.text = "User Info"
        supportActionBar!!.setDisplayShowTitleEnabled(false);
        context = this
        mDialog = Dialog(this)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        swipe_refresh.setOnRefreshListener(this)
        userInfoViewModel= ViewModelProviders.of(this).get(UserInfoViewModel::class.java)

        rv_user_list!!.layoutManager = LinearLayoutManager(context)
        rv_user_list!!.adapter = userInfoListAdapter

        loadDataInRecyclerView()

        userInfoViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        userInfoViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t) })
        userInfoViewModel!!.userInfoListLiveData
            .observe(this, Observer { it: ArrayList<UserInfoViewModel> ->
                userInfoListAdapter.setArrayList(it)
            })
    }
    private fun loadDataInRecyclerView() {
        if (NetworkConnection.isNetworkConnected()) {
            userInfoViewModel!!.getUserInfoList()
        } else {
            showError(true, "Check your internet connection. Please try again!!!")
        }
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = false
        loadDataInRecyclerView()
    }
    private fun showError(showError: Boolean, message: String) {
        if (showError) {
            Toast.makeText(
                OptusDemoApplication.context,
                message,
                Toast.LENGTH_SHORT
            )
                .show()

        }
    }

    private fun showProgressDialog() {
        mDialog.setContentView(R.layout.progress_dialog_layout)
        mDialog.show()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            showProgressDialog()
        } else {
            hideLoading()
        }
    }

    private fun hideLoading() {
        if (mDialog.isShowing) {
            mDialog.hide()
        }
    }
}