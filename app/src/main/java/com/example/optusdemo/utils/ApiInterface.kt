package com.example.optusdemo.utils

import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiInterface {
    // This API returns the list of employees from server
    @GET("/users")
    fun getUserInfoList(): Observable<ArrayList<UserInfoViewModel>>

    @GET("/photos")
    fun getAlbumList(@Query("albumId") genre_id: Int): Observable<ArrayList<AlbumDetailViewModel>>
}