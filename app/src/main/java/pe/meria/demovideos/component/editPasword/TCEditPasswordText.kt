package pe.meria.demovideos.component.editPasword

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import androidx.appcompat.content.res.AppCompatResources
import pe.meria.demovideos.R
import pe.meria.demovideos.component.editNormal.CustomEditText
import pe.meria.demovideos.extensions.passwordAndIconTransformation

class TCEditPasswordText(context: Context, attributeSet: AttributeSet) : CustomEditText(context, attributeSet) {
    private val drawableVisible = AppCompatResources.getDrawable(context, R.drawable.ic_eye)
    private val drawableGone = AppCompatResources.getDrawable(context, R.drawable.ic_eye_not)

    private val drawables = Pair(drawableVisible, drawableGone)

    init {
        filters()
        drawablesSetUp()
        drawableClickEvent()
        setUp(true)
    }

    fun setUp(value:Boolean){
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        if (value){
            editText.imeOptions = EditorInfo.IME_ACTION_NEXT
        }else{
            editText.imeOptions = EditorInfo.IME_ACTION_DONE
        }

    }

    private fun drawablesSetUp() {
        this.editText.setCompoundDrawablesWithIntrinsicBounds(null, null, this.drawables.second, null)
    }

    private fun drawableClickEvent() {
        this.editText.passwordAndIconTransformation(if (this.drawables.first==null)this.drawables.first else null, if (this.drawables.second==null)this.drawables.second else null)
    }

    private fun filters() {
        this.editText.transformationMethod = PasswordTransformationMethod.getInstance()
        this.editText.filters = arrayOf(
            InputFilter { source, _, _, _, _, _ ->
                source.toString().filterNot { it.isWhitespace() }
            }
        )
    }
}