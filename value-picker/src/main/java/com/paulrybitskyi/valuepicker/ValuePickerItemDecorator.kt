package com.paulrybitskyi.valuepicker

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.ktx.applyBounds
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.ValueItemConfig
import kotlin.math.ceil
import kotlin.math.floor

internal class ValuePickerItemDecorator(
    var areDividersEnabled: Boolean,
    var dividerDrawable: Drawable,
    var maxVisibleItems: Int,
    val valueItemConfigProvider: () -> ValueItemConfig,
    val orientationProvider: () -> Orientation
) : RecyclerView.ItemDecoration() {


    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        if(areDividersEnabled) {
            when(orientationProvider()) {
                Orientation.VERTICAL -> canvas.drawVerticalDividers(parent)
                Orientation.HORIZONTAL -> canvas.drawHorizontalDividers(parent)
            }
        }
    }


    private fun Canvas.drawVerticalDividers(parent: RecyclerView) {
        drawTopDivider(parent)
        drawBottomDivider(parent)
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


    private fun Canvas.drawHorizontalDividers(parent: RecyclerView) {
        drawLeftDivider(parent)
        drawRightDivider(parent)
    }


    private fun Canvas.drawLeftDivider(parent: RecyclerView) {
        val config = valueItemConfigProvider()
        val indexOfValueBeforeCenter = floor(maxVisibleItems / 2f).toInt()
        val drawableLeftBound = (config.size.width * indexOfValueBeforeCenter)
        val drawableRightBound = (drawableLeftBound + dividerDrawable.intrinsicWidth)

        dividerDrawable.applyBounds(
            left = drawableLeftBound,
            top = 0,
            right = drawableRightBound,
            bottom = parent.height
        )
        dividerDrawable.draw(this)
    }


    private fun Canvas.drawRightDivider(parent: RecyclerView) {
        val config = valueItemConfigProvider()
        val indexOfCenterValue = ceil(maxVisibleItems / 2f).toInt()
        val drawableRightBound = (config.size.width * indexOfCenterValue)
        val drawableLeftBound = (drawableRightBound - dividerDrawable.intrinsicWidth)

        dividerDrawable.applyBounds(
            left = drawableLeftBound,
            top = 0,
            right = drawableRightBound,
            bottom = parent.height
        )
        dividerDrawable.draw(this)
    }


}