package pe.meria.demovideos.component.editNormal

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintSet
import pe.meria.demovideos.extensions.appearWithAnim
import pe.meria.demovideos.extensions.disappearWithAnim
import pe.meria.demovideos.extensions.dpToPx
import pe.meria.demovideos.extensions.invisible
import pe.meria.demovideos.utils.EMPTY

open class CSErrorTextView(context: Context, attributeSet: AttributeSet?) : CustomEditTextStyle(context, attributeSet), CustomEditTextStyle.ICSEditTextStyle {

    protected val errorTextView: AppCompatTextView = AppCompatTextView(context)

    var errorMessage: String = EMPTY
        set(value) {
            field = value
            invalidate()
            updateErrorInfo
        }

    init {
        this.listener = this
        setUpErrorErrorTextView()
    }

    private fun setUpErrorErrorTextView() {
        this.errorTextView.id = View.generateViewId()
        this.errorTextView.layoutParams = LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT)
        this.addView(this.errorTextView)
        assignErrorTextViewConstraints()
        this.errorTextView.invisible
    }

    private val updateErrorInfo: Unit
        get() {
            this.errorTextView.text = this.errorMessage
            if (this.editText.isEnabled) this.errorTextView.appearWithAnim(this)
        }

    private fun assignErrorTextViewConstraints() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.connect(this.errorTextView.id, ConstraintSet.START, this.editText.id, ConstraintSet.START)
        constraintSet.connect(this.errorTextView.id, ConstraintSet.END, this.editText.id, ConstraintSet.END)
        constraintSet.connect(this.errorTextView.id, ConstraintSet.TOP, this.editText.id, ConstraintSet.BOTTOM, 5.dpToPx)
        constraintSet.connect(this.errorTextView.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.applyTo(this)
    }

    override fun resetStyle(editText: CustomEditTextStyle) {
        if (this.errorTextView.visibility == View.VISIBLE) this.errorTextView.disappearWithAnim
    }
}