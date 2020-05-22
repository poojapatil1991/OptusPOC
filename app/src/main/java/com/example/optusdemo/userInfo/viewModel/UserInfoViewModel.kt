package com.example.optusdemo.userInfo.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.optusdemo.executer.IExecuterThread
import com.example.optusdemo.executer.UIThread
import com.example.optusdemo.module.ThreadModule
import com.example.optusdemo.userInfo.UserInfoListUseCase
import rx.Subscriber

class UserInfoViewModel : ViewModel(){
    var id: String = " "
    var name: String = " "
    var username: String = " "
    var email: String = " "
    var phone: String = " "
    var website: String = " "
    var address : AddressViewModel = AddressViewModel()
    var company: CompanyViewModel = CompanyViewModel()

    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var userInfoListLiveData = MutableLiveData<ArrayList<UserInfoViewModel>>()

    private val uiThread: UIThread = ThreadModule().providePostExecutionThread()
    private val executorThread: IExecuterThread = ThreadModule().provideExecutorThread()

    private val userInfoListUseCase: UserInfoListUseCase =
        UserInfoListUseCase(executorThread, uiThread)

    fun getUserInfoList() {
        loadingError.value = false
        loading.value = true
        userInfoListUseCase.execute(UserInfoListSubscriber())
    }


    /*
   Subscriber to show image list on UI
   as soon as image list downloads from server it get notifies and show list of images on UI
    */
    inner class UserInfoListSubscriber : Subscriber<ArrayList<UserInfoViewModel>>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loading.value = false
            loadingError.value = true
            Log.e("UserInfoListAPI", e.toString())
        }

        override fun onNext(userInfoList: ArrayList<UserInfoViewModel>) {
            userInfoListLiveData.value = userInfoList
            loadingError.value = false
            loading.value = false
        }
    }
}