package pe.meria.demovideos.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import pe.meria.demovideos.R
import pe.meria.demovideos.component.editNormal.CustomEditText
import pe.meria.demovideos.utils.AppUtils
import pe.meria.demovideos.utils.cantDelayButtonClick
import pe.meria.demovideos.utils.problemLog

val View.gone:Unit get(){ this.visibility = View.GONE }

val View.visible: Unit get() { this.visibility = View.VISIBLE }

val View.invisible: Unit get() { this.visibility = View.INVISIBLE }

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
        return this.resources.displayMetrics.widthPixels
    }

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Activity.screenHeightSize: Int
    get() {
        return this.resources.displayMetrics.heightPixels
    }


fun AppCompatEditText.passwordAndIconTransformation(
    drawableWhenVisible: Drawable?,
    drawableWhenHide: Drawable?
) {
    var click = false
    this.drawableClick(null, {
        if (!click) {
            click = true
            this.changeDrawable(null, drawableWhenVisible)
            this.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            click = false
            this.changeDrawable(null, drawableWhenHide)
            this.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun AppCompatEditText.drawableClick(
    drawableStartClickAction: DrawableStartClickAction?,
    drawableEndClickAction: DrawableEndClickAction?
) {
    this.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            this.assignDrawableClicks(event, drawableStartClickAction, drawableEndClickAction)
        }
        false
    }
}

fun AppCompatEditText.assignDrawableClicks(
    event: MotionEvent, drawableStartClickAction: DrawableStartClickAction?,
    drawableEndClickAction: DrawableEndClickAction?
): Boolean {
    drawableStartClickAction?.let {
        if (event.rawX <= (this.compoundDrawables[0].bounds.width()) + this.paddingLeft) {
            it()
            return true
        }
    }
    drawableEndClickAction?.let {
        if (event.rawX >= this.right - this.compoundDrawables[2].bounds.width() - this.paddingRight) {
            it()
            return true
        }
    }
    return false
}


fun AppCompatEditText.changeDrawable(
    drawableStart: Drawable?,
    drawableEnd: Drawable?
) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawableStart, null, drawableEnd, null)
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