package com.example.optusdemo.utils

import com.example.optusdemo.albumList.viewModel.AlbumDetailViewModel
import com.example.optusdemo.userInfo.viewModel.UserInfoViewModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/*
*Interface for API call
 */

interface ApiInterface {
    // This API returns the list of users from server
    @GET("/users")
    fun getUserInfoList(): Observable<ArrayList<UserInfoViewModel>>

    // This API returns the list of photos from album
    @GET("/photos")
    fun getAlbumList(@Query("albumId") album_id: Int): Observable<ArrayList<AlbumDetailViewModel>>
}