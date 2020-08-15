package com.paulrybitskyi.valuepicker

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.ktx.applyBounds
import com.paulrybitskyi.valuepicker.ValueItemConfig
import kotlin.math.ceil
import kotlin.math.floor

internal class ValuePickerItemDecorator(
    var areDividersEnabled: Boolean,
    var dividerDrawable: Drawable,
    var maxVisibleItems: Int,
    val valueItemConfigProvider: () -> ValueItemConfig
) : RecyclerView.ItemDecoration() {


    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        if(areDividersEnabled) {
            canvas.drawTopDivider(parent)
            canvas.drawBottomDivider(parent)
        }
    }


    private fun Canvas.drawTopDivider(parent: RecyclerView) {
        val config = valueItemConfigProvider()
        val indexOfValueBeforeCenter = floor(maxVisibleItems / 2f).toInt()
        val drawableTopBound = (config.size.height * indexOfValueBeforeCenter)
        val drawableBottomBound = (drawableTopBound + dividerDrawable.intrinsicHeight)

        dividerDrawable.applyBounds(
            left = 0,
            top = drawableTopBound,
            right = parent.width,
            bottom = drawableBottomBound
        )
        dividerDrawable.draw(this)
    }


    private fun Canvas.drawBottomDivider(parent: RecyclerView) {
        val config = valueItemConfigProvider()
        val indexOfCenterValue = ceil(maxVisibleItems / 2f).toInt()
        val drawableBottomBound = (config.size.height * indexOfCenterValue)
        val drawableTopBound = (drawableBottomBound - dividerDrawable.intrinsicHeight)

        dividerDrawable.applyBounds(
            left = 0,
            top = drawableTopBound,
            right = parent.width,
            bottom = drawableBottomBound
        )
        dividerDrawable.draw(this)
    }


}