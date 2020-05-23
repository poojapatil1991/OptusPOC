package com.example.optusdemo.albumList.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.optusdemo.R
import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import com.example.optusdemo.utils.NetworkConnection
import com.example.optusdemo.utils.OptusDemoApplication
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.view.*
import kotlinx.android.synthetic.main.content_user_info.*

class AlbumListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    LifecycleOwner {

    private var albumListAdapter: AlbumListAdapter =
        AlbumListAdapter(ArrayList<AlbumDetailViewModel>())
    private var context: AlbumListActivity? = null
    private var albumDetailsViewModel: AlbumDetailViewModel? = null
    private lateinit var mDialog: Dialog
    private lateinit var albumID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        albumID = intent.getStringExtra("ALBUM_ID")
        setSupportActionBar(toolbar)
        //Toast.makeText(OptusDemoApplication.context,albumID,Toast.LENGTH_SHORT).show()
        toolbar.user_info_toolbar_title.text = "Album ID: " + albumID
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        context = this
        mDialog = Dialog(this)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        swipe_refresh.setOnRefreshListener(this)
        albumDetailsViewModel= ViewModelProviders.of(this).get(AlbumDetailViewModel::class.java)

        rv_user_list!!.layoutManager = LinearLayoutManager(context)
        rv_user_list!!.adapter = albumListAdapter

        loadDataInRecyclerView()

        albumDetailsViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        albumDetailsViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t) })
        albumDetailsViewModel!!.albumDetailsListLiveData
            .observe(this, Observer { t:ArrayList<AlbumDetailViewModel> ->
                albumListAdapter.setArrayList(t)
            })
    }
    private fun loadDataInRecyclerView() {
        if (NetworkConnection.isNetworkConnected()) {
            albumDetailsViewModel!!.getalbumList(albumID)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
