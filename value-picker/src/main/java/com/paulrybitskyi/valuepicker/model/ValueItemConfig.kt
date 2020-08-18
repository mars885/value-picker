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

@file:JvmName("ValueItemConfigUtils")

package com.paulrybitskyi.valuepicker.model

import android.graphics.Typeface


internal data class ValueItemConfig(
    val size: Size,
    val textColor: Int,
    val textSize: Float,
    val textTypeface: Typeface
)


@JvmField
internal val VALUE_ITEM_CONFIG_STUB = ValueItemConfig(
    size = sizeOf(width = 0, height = 0),
    textColor = 0,
    textSize = 0f,
    textTypeface = Typeface.SANS_SERIF
)