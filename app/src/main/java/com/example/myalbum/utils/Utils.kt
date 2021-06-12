package com.example.myalbum.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myalbum.R


object Utils {
    /**
     * Load images from a drawable.
     *
     * @param context context object
     * @param drawable Int drawable integer id
     * @param placeholder Int drawable integer id
     * @param errorHolder Int drawable integer id
     * @param imageView ImageView imageview object
     * @return void
     */
    fun loadImageFromDrawable(
        context: Context,
        drawable: Int,
        placeholder: Int,
        errorHolder: Int,
        imageView: ImageView
    ) {
        Glide.with(context)
            .load(drawable)
            .placeholder(placeholder)
            .error(errorHolder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }

    /**
     * Check if there is any internet connectivity
     * @param context
     * @return
     */
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    /**
     * Show error dialog
     * @param context Context
     * @param listener listener Can listen on tap of Ok button for further actions.
     * @return
     */
    fun showErrorDialog(
        context: Context,
        listener: DialogInterface.OnClickListener?) {
        val errorDialog: AlertDialog =
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.error_title))
                .setMessage(context.getString(R.string.error_desc))
                .setPositiveButton(context.getString(R.string.error_ok), listener)
                .setCancelable(false)
                .create()
        errorDialog.show()
    }
}