package com.example.optusdemo.albumList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.optusdemo.albumList.AlbumListUseCase
import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import com.example.optusdemo.module.ThreadModule
import rx.Subscriber

/*
* AlbumDetailViewModel ViewModel for the AlbumList Activity.
* loadingError is true if we get error while API call else it is false
* loading is true till we get result from API, once we get result it set to false
* albumDetailsListLiveData gives the list of photos inside the album
* albumListListUseCase is used to call the API
* AlbumListSubscriber subscriber observes on background thread and publish result on UI thread
 */

class AlbumDetailViewModel : ViewModel() {
    var albumId: String = ""
    var id: String = ""
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

    fun getAlbumList(albumID: String) {
        loadingError.value = false
        loading.value = true
        albumListListUseCase.albumId = albumID
        albumListListUseCase.execute(AlbumListSubscriber())
    }


    /*
   *Subscriber to show image list on UI
   *as soon as image list downloads from server it get notifies and show list of images on UI
    */

    inner class AlbumListSubscriber : Subscriber<ArrayList<AlbumDetailViewModel>>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loading.value = false
            loadingError.value = true
            Log.e("AlbumListAPI", e.toString())
        }

        override fun onNext(userInfoList: ArrayList<AlbumDetailViewModel>) {
            albumDetailsListLiveData.value = userInfoList
            loadingError.value = false
            loading.value = false
        }
    }
}
