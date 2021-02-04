package pe.meria.demovideos.extensions

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import pe.meria.demovideos.utils.cantDelayButtonClick
import pe.meria.demovideos.utils.problemLog

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.validateVisibility(value: Boolean) {
    if (value)this.visible() else this.gone()
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