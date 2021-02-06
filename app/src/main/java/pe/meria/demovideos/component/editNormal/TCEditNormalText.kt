package pe.meria.demovideos.component.editNormal

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo


class TCEditNormalText(context: Context, attributeSet: AttributeSet) : CustomEditText(context, attributeSet) {

    init {
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
}