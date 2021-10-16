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

package com.paulrybitskyi.valuepicker.scrollerhelpers.concrete

import com.paulrybitskyi.valuepicker.scrollerhelpers.ScrollerHelper

internal class InfiniteScrollerHelper(override var dataSetItemCount: Int) : ScrollerHelper {

    override val adapterItemCount: Int
        get() = (if (dataSetItemCount == 0) 0 else Integer.MAX_VALUE)

    override fun calculateAdapterPosition(dataSetPosition: Int): Int {
        val halfAdapterItemCount = (adapterItemCount / 2)
        val baseAdapterPosition = ((halfAdapterItemCount / dataSetItemCount) * dataSetItemCount)

        return (baseAdapterPosition + dataSetPosition)
    }

    override fun calculateDataSetPosition(adapterPosition: Int): Int {
        return (adapterPosition % dataSetItemCount)
    }
}
