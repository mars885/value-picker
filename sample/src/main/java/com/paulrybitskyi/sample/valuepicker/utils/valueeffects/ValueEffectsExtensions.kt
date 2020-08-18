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

package com.paulrybitskyi.sample.valuepicker.utils.valueeffects

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation


internal fun RecyclerView.calculateCenter(orientation: Orientation): Float {
    return (getDimension(orientation) * 0.5f)
}


internal fun RecyclerView.getDimension(orientation: Orientation): Int {
    return when(orientation) {
        Orientation.VERTICAL -> height
        Orientation.HORIZONTAL -> width
    }
}


internal fun View.calculateChildCenter(orientation: Orientation): Int {
    return when(orientation) {
        Orientation.VERTICAL -> ((height / 2) + top)
        Orientation.HORIZONTAL -> ((width / 2) + left)
    }
}