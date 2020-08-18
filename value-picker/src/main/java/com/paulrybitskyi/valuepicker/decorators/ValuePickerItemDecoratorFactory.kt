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

package com.paulrybitskyi.valuepicker.decorators

import android.graphics.drawable.Drawable
import com.paulrybitskyi.valuepicker.decorators.concrete.HorizontalValuePickerItemDecorator
import com.paulrybitskyi.valuepicker.decorators.concrete.VerticalValuePickerItemDecorator
import com.paulrybitskyi.valuepicker.model.ValueItemConfig

internal object ValuePickerItemDecoratorFactory {


    fun createVerticalDecorator(
        maxVisibleItems: Int,
        dividerDrawable: Drawable,
        valueItemConfigProvider: () -> ValueItemConfig
    ): ValuePickerItemDecorator {
        return VerticalValuePickerItemDecorator(
            maxVisibleItems = maxVisibleItems,
            dividerDrawable = dividerDrawable,
            valueItemConfigProvider = valueItemConfigProvider
        )
    }


    fun createHorizontalDecorator(
        maxVisibleItems: Int,
        dividerDrawable: Drawable,
        valueItemConfigProvider: () -> ValueItemConfig
    ): ValuePickerItemDecorator {
        return HorizontalValuePickerItemDecorator(
            maxVisibleItems = maxVisibleItems,
            dividerDrawable = dividerDrawable,
            valueItemConfigProvider = valueItemConfigProvider
        )
    }


}