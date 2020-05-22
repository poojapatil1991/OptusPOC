package com.example.optusdemo.albumList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.optusdemo.albumList.AlbumListUseCase
import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import com.example.optusdemo.module.ThreadModule
import rx.Subscriber

class AlbumDetailViewModel : ViewModel() {
    var albumId: String = ""
    var id : String = ""
    var title: String = ""
    var url: String = ""
    var thumbnailUrl: String = ""

    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var albumDetailsListLiveData = MutableLiveData<ArrayList<AlbumDetailViewModel>>()

    private val uiThread: UIThread = ThreadModule().providePostExecutionThread()
    private val executorThread: IExecuterThread = ThreadModule().provideExecutorThread()

    private val albumListListUseCase: AlbumListUseCase =
        AlbumListUseCase(executorThread, uiThread)

    fun getalbumList(albumID: String) {
        loadingError.value = false
        loading.value = true
        albumListListUseCase.albumId = albumID
        albumListListUseCase.execute(AlbumListSubscriber())
    }


    /*
   Subscriber to show image list on UI
   as soon as image list downloads from server it get notifies and show list of images on UI
    */
    inner class AlbumListSubscriber : Subscriber<ArrayList<AlbumDetailViewModel>>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loading.value = false
            loadingError.value = true
            Log.e("UserInfoListAPI", e.toString())
        }

        override fun onNext(userInfoList: ArrayList<AlbumDetailViewModel>) {
            albumDetailsListLiveData.value = userInfoList
            loadingError.value = false
            loading.value = false
        }
    }
}