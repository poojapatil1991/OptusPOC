package com.example.optusdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.optusdemo.utils.OptusDemoApplication.Companion.context

class NetworkConnection {
    companion object {
        /**
         * checking internet is available or not
         */
        fun isNetworkConnected(): Boolean {
            var isConnected: Boolean = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = connectivityManager.activeNetwork

                val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

                isConnected = networkCapabilities != null &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                isConnected = cm.isActiveNetworkMetered
            }
            return isConnected
        }
    }
}
