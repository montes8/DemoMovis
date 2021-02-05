package pe.meria.demovideos.extensions

import android.app.Activity
import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import pe.meria.demovideos.R
import pe.meria.demovideos.utils.AppUtils
import pe.meria.demovideos.utils.cantDelayButtonClick
import pe.meria.demovideos.utils.problemLog

val View.gone:Unit get(){ this.visibility = View.GONE }

val View.visible:Unit get(){ this.visibility = View.VISIBLE }

fun ImageView.loadImageUrlPicasso(imageUrl : String,view :View){
    if (imageUrl.isNotEmpty()) {
        if (AppUtils.isConnected(view.context)){
            Picasso.with(view.context).load(imageUrl).into(this,object : Callback {
                override fun onSuccess() { view.gone }

                override fun onError() { view.gone }
            })
        }else{
            view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.purple_100))
            view.gone
        }
    }
}

fun Activity?.showDialogCustom(resourceId: Int, func: Dialog.() -> Unit) {
    val dialog = Dialog(this ?: return)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setContentView(resourceId)
    dialog.func()
    dialog.show()
}

val Activity.screenWidthSize: Int
    get() {
        val metrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getRealMetrics(metrics)
        return metrics.widthPixels
    }

val Activity.screenHeightSize: Int
    get() {
        val metrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getRealMetrics(metrics)
        return metrics.heightPixels
    }



fun View?.delayClickState(timeMillis: Long = 300) {
    this?.let {
        it.apply { it.isEnabled = false }
        Handler(Looper.getMainLooper()).postDelayed({
            it.apply { it.isEnabled = true }
        }, timeMillis)
    } ?: run {
        Log.d(problemLog, cantDelayButtonClick)
    }
}