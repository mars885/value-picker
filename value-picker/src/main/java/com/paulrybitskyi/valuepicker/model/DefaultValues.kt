/*
 * Copyright 2020 Paul Rybitskyi, oss@paulrybitskyi.com
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

@file:JvmName("DefaultValuesUtils")

package com.paulrybitskyi.valuepicker.model

import android.content.Context
import android.graphics.Typeface
import com.paulrybitskyi.commons.ktx.getCompatColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.valuepicker.R

internal class DefaultValues(
    val valueItemTextColor: Int,
    val valueItemMinWidth: Int,
    val valueItemMinHeight: Int,
    val valueItemPadding: Int,
    val valueItemTextSize: Float,
    val valueItemTextTypeface: Typeface,
)

internal val DefaultValues.valueItemSize: Size
    get() = sizeOf(
        width = valueItemMinWidth,
        height = valueItemMinHeight,
    )

internal fun initDefaultValues(context: Context): DefaultValues {
    return DefaultValues(
        valueItemTextColor = context.getCompatColor(R.color.default_value_item_text_color),
        valueItemMinWidth = context.getDimensionPixelSize(R.dimen.default_value_item_min_width),
        valueItemMinHeight = context.getDimensionPixelSize(R.dimen.default_value_item_min_height),
        valueItemPadding = context.getDimensionPixelSize(R.dimen.default_value_item_padding),
        valueItemTextSize = context.getDimension(R.dimen.default_value_item_text_size),
        valueItemTextTypeface = Typeface.SANS_SERIF,
    )
}
