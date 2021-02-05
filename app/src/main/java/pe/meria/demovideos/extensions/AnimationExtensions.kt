package pe.meria.demovideos.extensions

import android.view.View
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator


fun ConstraintLayout.outOfScreenAnimation(activity: AppCompatActivity) {
    val set = AnimationSet(true)
    val animAlpha: Animation = AlphaAnimation(1.0f, 0.0f)
    set.addAnimation(animAlpha)

    val animation: Animation = TranslateAnimation(0f, activity.screenWidthSize.toFloat(), 0f, 0f)
    set.addAnimation(animation)

    set.interpolator = LinearInterpolator()
    set.duration = 500
    set.fillAfter = true

    this.startAnimation(set)
    this.gone
    set.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}

fun ConstraintLayout.enterFromOutOfScreenAnimation(activity: AppCompatActivity) {
    this.visible
    val set = AnimationSet(true)
    val animAlpha: Animation = AlphaAnimation(0.0f, 1.0f)
    set.addAnimation(animAlpha)

    val animation: Animation = TranslateAnimation(activity.screenWidthSize.toFloat(), 0f, 0f, 0f)
    set.addAnimation(animation)

    set.interpolator = OvershootInterpolator(0.5f)
    set.duration = 500

    this.startAnimation(set)

    set.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}

val AppCompatActivity.animForm: AnimationSet
    get() {
        val set = AnimationSet(true)
        val alphaAnim = AlphaAnimation(0f, 1f)
        set.addAnimation(alphaAnim)

        val translateAnim = TranslateAnimation(0f, 0f, this.screenWidthSize / 2.toFloat(), 0f)
        set.addAnimation(translateAnim)

        val scaleAnim = ScaleAnimation(0.95f, 1f, 0.95f, 1f, 50f, 50f)
        set.addAnimation(scaleAnim)

        set.duration = 900
        set.interpolator = OvershootInterpolator(0.67f)
        return set
    }

val View.animAlphaAndAppear: Unit
    get() {
        this.visible
        val set = AnimationSet(true)
        val alphaAnim = AlphaAnimation(0f, 1f)
        set.addAnimation(alphaAnim)
        val scaleAnim = ScaleAnimation(0.7f, 1f, 0.7f, 1f, 50f, 50f)
        set.addAnimation(scaleAnim)
        set.duration = 300
        this.startAnimation(set)
    }

 fun View.visibleWithTransitionXLeftToPosition(activity: AppCompatActivity){
        if (this.visibility == View.GONE) {
            this.visible

            val set = AnimationSet(true)
            val anim = TranslateAnimation(-activity.screenWidthSize / 2.toFloat(), 0f, 0f, 0f)
            set.addAnimation(anim)

            val animAlpha: Animation = AlphaAnimation(0.0f, 1.0f)
            set.addAnimation(animAlpha)

            set.duration = 400
            set.interpolator = DecelerateInterpolator()
            this.startAnimation(set)

            this.isEnabled = true
        }
    }

 fun AppCompatTextView.visibleWithTransitionXRightToPosition(activity: AppCompatActivity){
        if (this.visibility == View.GONE) {
            this.visible

            val set = AnimationSet(true)

            val anim = TranslateAnimation(activity.screenWidthSize.toFloat(), 0f, 0f, 0f)
            set.addAnimation(anim)

            val animAlpha: Animation = AlphaAnimation(0.0f, 1.0f)
            set.addAnimation(animAlpha)

            set.duration = 400
            set.interpolator = DecelerateInterpolator()
            this.startAnimation(set)

            this.isEnabled = true
        }
    }

 fun View.goneWithTransitionXPositionToRight(activity: AppCompatActivity){
        if (this.visibility == View.VISIBLE) {
            this.isEnabled = false

            val set = AnimationSet(true)
            val anim = TranslateAnimation(0f, activity.screenWidthSize.toFloat(), 0f, 0f)
            set.addAnimation(anim)

            val animAlpha: Animation = AlphaAnimation(1.0f, 0.0f)
            set.addAnimation(animAlpha)
            set.duration = 400
            set.interpolator = DecelerateInterpolator()
            this.startAnimation(set)

            set.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    this@goneWithTransitionXPositionToRight.gone
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })
        }
    }

 fun AppCompatTextView.goneWithTransitionXPositionToLeft(activity: AppCompatActivity){
        if (this.visibility == View.VISIBLE) {
            this.isEnabled = false

            val set = AnimationSet(true)
            val anim = TranslateAnimation(0f, -activity.screenWidthSize / 2.toFloat(), 0f, 0f)
            set.addAnimation(anim)

            val animAlpha: Animation = AlphaAnimation(1.0f, 0.0f)
            set.addAnimation(animAlpha)

            set.duration = 400
            set.interpolator = DecelerateInterpolator()
            this.startAnimation(set)
            this.gone
        }
    }

 fun AppCompatImageButton.enterAnimBottomToPosition(activity: AppCompatActivity){
        this.isEnabled = false

        val set = AnimationSet(true)
        val anim = TranslateAnimation(0f, 0f, activity.screenHeightSize.toFloat(), 0f)
        set.addAnimation(anim)

        val animAlpha: Animation = AlphaAnimation(0.0f, 1.0f)
        set.addAnimation(animAlpha)

        set.duration = 400
        set.interpolator = LinearOutSlowInInterpolator()
        this.startAnimation(set)

        this.isEnabled = true
    }

fun View.tooltipDisappearAnim(button: AppCompatImageButton) {
    val set = AnimationSet(true)
    val alphaAnim = AlphaAnimation(1f, 0f)
    set.addAnimation(alphaAnim)
    val scaleAnim = ScaleAnimation(1f, 0.7f, 1f, 0.7f, 50f, 50f)
    set.addAnimation(scaleAnim)
    set.startOffset = 3000
    set.duration = 300
    this.startAnimation(set)
    set.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
        }

        override fun onAnimationEnd(animation: Animation?) {
            this@tooltipDisappearAnim.gone
            button.isEnabled = true
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}

fun View.tooltipAnim(button: AppCompatImageButton){
    this.visible
    val set = AnimationSet(true)
    val alphaAnim = AlphaAnimation(0f, 1f)
    set.addAnimation(alphaAnim)
    val scaleAnim = ScaleAnimation(0.7f, 1f, 0.7f, 1f, 50f, 50f)
    set.addAnimation(scaleAnim)
    set.duration = 300
    this.startAnimation(set)
    set.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            button.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animation?) {
            this@tooltipAnim.tooltipDisappearAnim(button)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}