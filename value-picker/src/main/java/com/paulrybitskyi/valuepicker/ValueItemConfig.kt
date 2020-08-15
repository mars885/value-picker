package com.paulrybitskyi.valuepicker

import android.graphics.Typeface


internal data class ValueItemConfig(
    val size: Size,
    val textColor: Int,
    val textSize: Float,
    val typeface: Typeface
)


internal val VALUE_ITEM_CONFIG_STUB = ValueItemConfig(
    size = sizeOf(width = 0, height = 0),
    textColor = 0,
    textSize = 0f,
    typeface = Typeface.SANS_SERIF
)