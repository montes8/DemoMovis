package pe.meria.demovideos.component.editNormal

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import pe.meria.demovideos.R
import pe.meria.demovideos.utils.EMPTY

open class CustomEditText(context: Context, private val attributeSet: AttributeSet?) : CustomErrorTextViewStyle(context, attributeSet) {
    constructor(context: Context) : this(context, null)

    var etListener: ITCSimpleEditText?= null

    val hideError: Unit
        get() {
            editTextNormalStyle()
        }

    val setEditText: AppCompatEditText
        get() = this.editText

    var textInput: String
        get() {
            return this.editText.text.toString()
        }
        set(value) {
            this.editText.setText(value)
        }

    var setCursorAtLast = true
        set(value) {
            field = value
            invalidate()
            this.editText.setSelection(this.textInput.length)
        }

    init {
        loadAttrs()
        this.editText.filters =  arrayOf(InputFilter.LengthFilter(50))
        focusEvent(null)
        doWhenTextChanged()
    }

    private fun loadAttrs() {
        val attrSet = context.obtainStyledAttributes(this.attributeSet, R.styleable.CustomEditText)

        this.placeholderText = attrSet.getString(R.styleable.CustomEditText_hint)
        this.placeholderTextValue = attrSet.getString(R.styleable.CustomEditText_text)?: EMPTY
        this.editTextEnabled = attrSet.getBoolean(R.styleable.CustomEditText_isEnabled, true)

        attrSet.recycle()
    }

    fun doWhenEditFinish(action: () -> Unit) {
        this.editText.doAfterTextChanged {
            editTextNormalStyle()
            action()
        }
    }

    fun doWhenTextChanged() {
        this.editText.doOnTextChanged { text, _, _, _ ->
            this.etListener?.onchangeListener(this)
        }
    }

    fun focusEvent(action: ((hasFocus: Boolean) -> Unit)?) {
        this.editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && this.editTextEnabled)this.etListener?.doWhenFocusGone(this)
            if (hasFocus) this.etListener?.doOnFocus(this)
            action?.let { it(hasFocus) }
        }
    }

    interface ITCSimpleEditText {
        fun onchangeListener(editText: CustomEditText)
        fun doWhenFocusGone(editText: CustomEditText)
        fun doOnFocus(editText: CustomEditText)
    }


}



