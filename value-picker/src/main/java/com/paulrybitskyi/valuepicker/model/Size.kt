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

import androidx.annotation.Px


private const val DIMENSION_NOT_SET = -1


/**
 * A size of the item inside the value picker.
 */
class Size internal constructor(
    val width: Int,
    val height: Int
) {


    companion object {

        /**
         * Creates an item size with a specified fixed width. Height will be
         * calculated based on the maximum height of the item text.
         *
         * @param width The width of the item in pixels
         *
         * @return The size with a fixed width and default height
         */
        @JvmStatic
        fun withFixedWidth(@Px width: Int): Size {
            return Size(
                width = width,
                height = DIMENSION_NOT_SET
            )
        }

        /**
         * Creates an item size with a specified fixed height. Width will be
         * calculated based on the maximum width of the item text.
         *
         * @param height The height of the item in pixels
         *
         * @return The size with a fixed height and default width
         */
        @JvmStatic
        fun withFixedHeight(@Px height: Int): Size {
            return Size(
                width = DIMENSION_NOT_SET,
                height = height
            )
        }

        /**
         * Creates an item size with a specified fixed width and height.
         *
         * @param width The width of the item in pixels
         * @param height The height of the item in pixels
         *
         * @return The size with a fixed width and height
         */
        @JvmStatic
        fun withFixedSize(@Px width: Int, @Px height: Int): Size {
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
