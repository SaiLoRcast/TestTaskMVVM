package com.example.mvvmtesttask.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmtesttask.R
import com.google.gson.Gson

import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorHandler {

//    fun handleError(error: Throwable, context: Context) {
//        var message = ""
//        if (error is UnknownHostException) {
//            message = context.getString(R.string.internet_error)
//        }
//
//        if (error is HttpException) {
//            when (error.code()) {
//                500 -> message = context.getString(R.string.internal_server_error)
//
//                401 -> message = context.getString(R.string.user_unauthorized)
//
//                409 -> {
//                    val response: BaseResponse = Gson().fromJson(error.response().errorBody()?.string(), BaseResponse::class.java)
//                    val buffer = StringBuilder("")
//                    for (item in response.errors) {
//                        buffer.append(item)
//                        buffer.append("\n")
//                    }
//                    message = buffer.toString()
//                }
//            }
//        }
//        showErrorDialog(message, context)
//    }
//
//    fun showErrorDialog(message: String, context: Context) {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//        builder.setTitle(context.getString(R.string.error))
//                .setMessage(message)
//                .setPositiveButton(android.R.string.yes) { _, _ ->
//                    if (message == context.getString(R.string.user_unauthorized)) {
//                        val intent = Intent(context, AuthActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        context.startActivity(intent)
//                        (context as MainActivity).finish()
//                    }
//                }
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun isOnline(context: Context): Boolean {
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
//            val capabilities =
//                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                when {
//                    capabilities.hasTransport(TRANSPORT_CELLULAR) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(TRANSPORT_WIFI) -> {
//                        return true
//                    }
//                    capabilities.hasTransport(TRANSPORT_ETHERNET) -> {
//                        return true
//                    }
//                }
//            }
//        }
//        return false
//    }

}
