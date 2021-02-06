package pe.meria.demovideos.component.editNormal

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import pe.meria.demovideos.R
import pe.meria.demovideos.extensions.dpToPx
import pe.meria.demovideos.utils.EMPTY

open class CustomEditTextStyle(context: Context, attributeSet: AttributeSet?) : CustomBaseEditText(context, attributeSet) {

    protected var listener: ICSEditTextStyle ?= null

    var placeholderText: String? = null
        set(value) {
            field = value
            invalidate()
            this.placeholderText?.let{ this.editText.hint = it }
        }

    var placeholderTextValue:String = EMPTY
        get() {
            return this.editText.text.toString()
        }
        set(value) {
            field = value
            invalidate()
             this.editText.setText(placeholderTextValue)
        }

    var drawableBackground: Drawable?= ContextCompat.getDrawable(context, R.drawable.bg_custom_gray_edit_text) ?: this.editText.background
        set(value) {
            field = value
            invalidate()
            this.editText.background = this.drawableBackground
        }

    var errorDrawableBackground = ContextCompat.getDrawable(context, R.drawable.bg_custom_red_stroke_text) ?: this.editText.background
        set(value) {
            field = value
            invalidate()
        }

    var etTextAppeareance: Int = R.style.Text12PoppinsRegularSelector
        set(value) {
            field = value
            invalidate()
            this.editText.setTextAppearance(this.etTextAppeareance)
        }

    var editTextEnabled: Boolean = true
        set(value) {
            field = value
            invalidate()
            editText.isEnabled = value
            if (!this.editTextEnabled){
                editTextNormalStyle()
            }
        }

    var etPaddingLeft: Int = 24
        set(value) {
            field = value
            invalidate()
        }

    var etPaddingTop: Int = 0
        set(value) {
            field = value
            invalidate()
            editTextNormalStyle()
        }

    var etPaddingRight: Int = 24
        set(value) {
            field = value
            invalidate()
        }

    var etPaddingBottom: Int = 0
        set(value) {
            field = value
            invalidate()
            editTextNormalStyle()
        }

    var editTextGravity: Int = Gravity.START
        set(value) {
            field = value
            invalidate()
            this.editText.gravity = this.editTextGravity
        }

    var etSingleLine = true
        set(value) {
            field = value
            invalidate()
            editTextNormalStyle()
        }

    var etHeight = context.resources.getDimension(R.dimen.size_fifty_six).toInt()
        set(value) {
            field = value
            invalidate()
            editTextNormalStyle()
        }

    init {
        editTextNormalStyle()
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    protected fun editTextNormalStyle() {
        this.listener?.resetStyle(this)
        this.editText.layoutParams.height = this.etHeight
        this.drawableBackground = ContextCompat.getDrawable(context, R.drawable.bg_custom_gray_edit_text)
        if (this.etSingleLine)
            this.editText.maxLines = 1
        else {
            this.editText.isSingleLine = false
            this.editText.maxLines = 100
        }
        this.editText.setTextAppearance(this.etTextAppeareance)
        this.editText.setPadding(etPaddingLeft.dpToPx, etPaddingTop.dpToPx, etPaddingRight.dpToPx, etPaddingBottom.dpToPx)
        this.editText.compoundDrawableTintList = AppCompatResources.getColorStateList(context, R.color.gray_350)
    }

    interface ICSEditTextStyle{
        fun resetStyle(editText:CustomEditTextStyle)
    }
}