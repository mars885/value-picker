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

@file:JvmName("OrientationUtils")

package com.paulrybitskyi.valuepicker.model

import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.ValuePickerView

/**
 * An orientation of the value picker.
 *
 * @see [ValuePickerView]
 * @see [ValuePickerView.orientation]
 */
enum class Orientation(internal val id: Int) {

    VERTICAL(id = 1),
    HORIZONTAL(id = 2),
    ;

companion object {

        @JvmName("forId")
        @JvmStatic
        internal fun Int.asOrientation(): Orientation {
            return values().find { it.id == this }
                ?: throw IllegalArgumentException("Could not find the orientation for the specified ID: $this.")
        }
    }
}

internal val Orientation.isVertical: Boolean
    get() = (this == Orientation.VERTICAL)

internal val Orientation.isHorizontal: Boolean
    get() = (this == Orientation.HORIZONTAL)

internal val Orientation.rvOrientation: Int
    get() = when (this) {
        Orientation.VERTICAL -> RecyclerView.VERTICAL
        Orientation.HORIZONTAL -> RecyclerView.HORIZONTAL
    }
