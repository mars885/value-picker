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

@file:JvmName("SizeUtils")

package com.paulrybitskyi.valuepicker.model


private const val DIMENSION_NOT_SET = -1


class Size internal constructor(
    val width: Int,
    val height: Int
) {


    companion object {

        @JvmStatic
        fun withFixedWidth(width: Int): Size {
            return Size(
                width = width,
                height = DIMENSION_NOT_SET
            )
        }

        @JvmStatic
        fun withFixedHeight(height: Int): Size {
            return Size(
                width = DIMENSION_NOT_SET,
                height = height
            )
        }

        @JvmStatic
        fun withFixedSize(width: Int, height: Int): Size {
            return Size(
                width = width,
                height = height
            )
        }

    }


    internal val hasWidth: Boolean
        get() = (width != DIMENSION_NOT_SET)


    internal val hasHeight: Boolean
        get() = (height != DIMENSION_NOT_SET)


    internal val hasBothDimensions: Boolean
        get() = (hasWidth && hasHeight)


}


internal fun sizeOf(width: Int, height: Int): Size {
    return Size(width, height)
}