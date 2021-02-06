
package pe.meria.demovideos.component.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class CustomVerticalSquareFrameLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context!!, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (widthSize == 0 && heightSize == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            val minSize = Math.min(measuredWidth, measuredHeight)
            setMeasuredDimension(minSize, minSize)
            return
        }
        val size: Int
        size = if (widthSize == 0 || heightSize == 0) {
            Math.max(widthSize, heightSize)
        } else {
            Math.min(widthSize, heightSize)
        }
        val newMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        super.onMeasure(newMeasureSpec-10, newMeasureSpec)
    }
}