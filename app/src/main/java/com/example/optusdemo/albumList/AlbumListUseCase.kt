package com.example.optusdemo.albumList

import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import com.example.optusdemo.module.ApiModule
import com.example.optusdemo.utils.ApiInterface
import com.example.optusdemo.utils.UseCase
import rx.Observable

class AlbumListUseCase (executorThreadI: IExecuterThread, postExecuterThread: UIThread) :
    UseCase<ArrayList<AlbumDetailViewModel>>(executorThreadI, postExecuterThread) {
    private var apiRequest: ApiInterface? = ApiModule().provideAllApi()
    var albumId : String = ""
    override fun createObservable(): Observable<ArrayList<AlbumDetailViewModel>> {
        return apiRequest!!.getAlbumList(albumId.toInt())
    }
}