package com.example.optusdemo.userInfo.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.optusdemo.userInfo.UserInfoListUseCase
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing
import rx.Observable

class UserInfoViewModelTest {
    @Mock
    lateinit var userInfoUseCase: UserInfoListUseCase

    @Mock
    lateinit var userInfoSubsciber: UserInfoViewModel.UserInfoListSubscriber

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Given UserInfoListUseCase returns data, when getUserInfoList() called, then update live data`() {

        var userInfoListData = MutableLiveData<ArrayList<UserInfoViewModel>>()
        whenever(userInfoUseCase.execute(userInfoSubsciber))
            .thenReturn(
                userInfoListData
            )
        //Fire the test method
        val myViewModel = UserInfoViewModel()
        myViewModel.userInfoListUseCase = userInfoUseCase
        myViewModel.getUserInfoList()
        //Check that our live data is updated
        Assert.assertEquals(userInfoListData, myViewModel.userInfoListLiveData.value)
    }
}

private fun <T> OngoingStubbing<T>.thenReturn(userInfoListData: MutableLiveData<ArrayList<UserInfoViewModel>>): ArrayList<UserInfoViewModel>? {
    return userInfoListData.value
}