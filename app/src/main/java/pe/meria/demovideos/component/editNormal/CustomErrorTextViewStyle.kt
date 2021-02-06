package pe.meria.demovideos.component.editNormal

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import pe.meria.demovideos.R

open class CustomErrorTextViewStyle(context: Context, attributeSet: AttributeSet?) : CSErrorTextView(context, attributeSet) {
    var errorGravity = Gravity.END
        set(value) {
            field = value
            invalidate()
            this.errorTextView.gravity = this.errorGravity
        }

    var errorTextAppearance = R.style.Text10PoppinsRegularRedError
        set(value) {
            field = value
            invalidate()
            this.errorTextView.setTextAppearance(this.errorTextAppearance)
        }

    init {
        this.errorTextView.gravity = this.errorGravity
        this.errorTextView.setTextAppearance(this.errorTextAppearance)
    }
}