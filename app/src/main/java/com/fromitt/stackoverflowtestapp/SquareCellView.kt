package com.fromitt.stackoverflowtestapp

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/*
* @author Tkachov Vasyl
* @since 13.10.2020
*/
class SquareCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        setMeasuredDimension(width, width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)
    }
}