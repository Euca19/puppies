package com.app.puppies.utils

import android.content.Context
import android.net.ConnectivityManager
import com.app.puppies.MyApplication

/**
 * Created by Eucaris Guerrero on 26-04-20.
 */
class NetworkConnection {
    fun isNetworkOnline(): Boolean {
        var status = false
        if (MyApplication.applicationContext() != null) {
            val cm = MyApplication.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    status = true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    status = true
                } else if (activeNetwork.isConnected) {
                    status = true
                }
            } else {
                // not connected to the internet
                status = false
            }
        }
        return status

    }
}