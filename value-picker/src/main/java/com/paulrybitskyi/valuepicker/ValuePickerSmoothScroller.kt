package com.paulrybitskyi.valuepicker

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller


private const val MILLISECONDS_PER_INCH = 150f


internal class ValuePickerSmoothScroller(context: Context) : LinearSmoothScroller(context) {


    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return (MILLISECONDS_PER_INCH / displayMetrics.densityDpi)
    }


}