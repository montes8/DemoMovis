package pe.meria.demovideos.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import pe.meria.demovideos.R

object AppUtils {

    fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
    }


    fun dialogGeneric(message: String?, context: Context) {
        val genericMessage = message ?: context.getString(R.string.error_go)
        AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppThemeBlue))
            .setTitle(R.string.app_name)
            .setCancelable(false)
            .setMessage(genericMessage)
            .setPositiveButton(R.string.dialog_accept) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    fun showErrorToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}