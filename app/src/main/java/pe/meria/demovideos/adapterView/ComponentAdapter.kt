package pe.meria.demovideos.adapterView

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import pe.meria.demovideos.R
import pe.meria.demovideos.utils.AppUtils
import java.util.*

object ComponentAdapter {

    @JvmStatic
    @BindingAdapter("animationSplash")
    fun animationSplash(view: View,type : Int) {
        if (type == 1){
            view.animation = AnimationUtils.loadAnimation(view.context, R.anim.ani_top)
        }else{
            view.animation = AnimationUtils.loadAnimation(view.context, R.anim.ani_bottom)
        }
    }


    @JvmStatic
    @BindingAdapter("setTextDouble")
    fun setTextDouble(view: TextView,value : Double) {
       view.text = value.toString()
    }

    @JvmStatic
    @BindingAdapter("setTextLanguageView")
    fun setTextLanguageView(view: TextView,value : String) {
        val locale = Locale(value)
        Locale.setDefault(locale)
        view.text = Locale.getDefault().language
    }

}