package pe.meria.demovideos.component.editNormal

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

open class CustomBaseEditText(context: Context, attributeSet: AttributeSet?) : ConstraintLayout(context, attributeSet) {
    constructor(context: Context) : this(context, null)

    protected val editText: AppCompatEditText = AppCompatEditText(context)

    init {
        this.editText.layoutParams = LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT)
        setUpEditText()
    }

    private fun setUpEditText() {
        this.editText.id = View.generateViewId()
        this.editText.layoutParams = LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT)
        this.addView(this.editText)
        assignEditTextConstraints()
    }

    private fun assignEditTextConstraints() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.connect(this.editText.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(this.editText.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(this.editText.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.applyTo(this)
    }
}