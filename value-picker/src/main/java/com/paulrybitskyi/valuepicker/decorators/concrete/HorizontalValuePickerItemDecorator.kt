/*
 * Copyright 2020 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.paulrybitskyi.valuepicker.decorators.concrete

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.ktx.applyBounds
import com.paulrybitskyi.valuepicker.decorators.ValuePickerItemDecorator
import com.paulrybitskyi.valuepicker.model.ValueItemConfig
import kotlin.math.ceil
import kotlin.math.floor

internal class HorizontalValuePickerItemDecorator(
    maxVisibleItems: Int,
    dividerDrawable: Drawable,
    valueItemConfigProvider: () -> ValueItemConfig
) : ValuePickerItemDecorator(maxVisibleItems, dividerDrawable, valueItemConfigProvider) {


    override fun drawDividers(canvas: Canvas, parent: RecyclerView) = with(canvas) {
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