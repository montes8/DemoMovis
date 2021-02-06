package pe.meria.demovideos.component.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet

class RoundImageView : androidx.appcompat.widget.AppCompatImageView {
    private lateinit var path: Path
    private lateinit var rect: RectF

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        path = Path()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())

        val radii = floatArrayOf(
            20f, 20f,
            0f, 0f,
            0f, 0f,
            20f, 20f
        )

     /*   val radii = floatArrayOf(
            topLeftCorner, bottomLeftCorner,
            topRightCorner, topRightCorner,
            bottomRightCorner, bottomRightCorner,
            bottomLeftCorner, bottomLeftCorner
        )*/


        path.addRoundRect(rect,radii, Path.Direction.CW)
        canvas.clipPath(path)
        super.onDraw(canvas)
    }
}