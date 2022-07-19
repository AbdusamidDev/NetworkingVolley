package develop.abdusamid.networkingvolley.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@SuppressLint("ObsoleteSdkInt")
fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    } else {
        val connectInfo = connectivityManager.activeNetworkInfo
        connectInfo != null && connectInfo.isConnected
    }
}