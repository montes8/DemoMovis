package pe.meria.demovideos.adapterView

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import pe.meria.demovideos.R

object ComponentAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty()) {
            Picasso.with(view.context).load(imageUrl).into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("animationSplash")
    fun animationSplash(view: View,type : Int) {
        if (type == 1){
            view.animation = AnimationUtils.loadAnimation(view.context, R.anim.ani_top)
        }else{
            view.animation = AnimationUtils.loadAnimation(view.context, R.anim.ani_bottom)
        }

    }

}