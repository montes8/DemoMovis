package pe.meria.demovideos.component.widget

import android.content.Context
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

class CustomViewPager : ViewPager {
    private var mScroller: FixedSpeedScroller? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    private fun init() {
        try {
            val viewpager: Class<*> = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            mScroller = FixedSpeedScroller(
                context,
                DecelerateInterpolator()
            )
            scroller[this] = mScroller
        } catch (ignored: Exception) {
        }
    }

    fun setScrollDuration(duration: Int) {
        mScroller!!.setScrollDuration(duration)
    }

    private inner class FixedSpeedScroller : Scroller {
        private var mDuration = 500

        constructor(context: Context?) : super(context) {}
        constructor(
            context: Context?,
            interpolator: Interpolator?
        ) : super(context, interpolator) {
        }

        constructor(
            context: Context?,
            interpolator: Interpolator?,
            flywheel: Boolean
        ) : super(context, interpolator, flywheel) {
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            super.startScroll(startX, startY, dx, dy, mDuration)
        }

        fun setScrollDuration(duration: Int) {
            mDuration = duration
        }
    }
}