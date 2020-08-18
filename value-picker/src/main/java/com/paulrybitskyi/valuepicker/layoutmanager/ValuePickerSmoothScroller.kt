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

@file:JvmName("ValuePickerSmoothScrollerUtils")

package com.paulrybitskyi.valuepicker.layoutmanager

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller


private const val MILLISECONDS_PER_INCH = 150f


internal class ValuePickerSmoothScroller(context: Context) : LinearSmoothScroller(context) {


    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return (MILLISECONDS_PER_INCH / displayMetrics.densityDpi)
    }


}