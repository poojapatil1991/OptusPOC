package com.example.optusdemo.userInfo

import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import com.example.optusdemo.module.ApiModule
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import com.example.optusdemo.utils.ApiInterface
import com.example.optusdemo.utils.UseCase
import rx.Observable

class UserInfoListUseCase (executorThreadI: IExecuterThread, postExecuterThread: UIThread) :
    UseCase<ArrayList<UserInfoViewModel>>(executorThreadI, postExecuterThread) {
    private var apiRequest: ApiInterface? = ApiModule().provideAllApi()

    override fun createObservable(): Observable<ArrayList<UserInfoViewModel>> {
        return apiRequest!!.getUserInfoList()
    }
}