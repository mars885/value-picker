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

package com.paulrybitskyi.sample.valuepicker.clothingsizepicker

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.commons.ktx.getDrawable
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.clothingsizepicker.model.ClothingSize
import com.paulrybitskyi.sample.valuepicker.utils.PickerItem
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.Size
import kotlinx.android.synthetic.main.fragment_clothing_size_picker.*

internal class ClothingSizePickerFragment : Fragment(R.layout.fragment_clothing_size_picker) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClothingSizePicker()
    }


    private fun initClothingSizePicker() = with(clothingSizePicker) {
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        maxVisibleItems = 3
        textSize = getDimension(R.dimen.clothing_size_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.DEFAULT_BOLD
        dividerDrawable = getDrawable(R.drawable.clothing_size_picker_divider)
        fixedItemSize = Size.withFixedSize(
            width = getDimensionPixelSize(R.dimen.clothing_size_picker_item_width),
            height = getDimensionPixelSize(R.dimen.clothing_size_picker_item_height)
        )
        orientation = Orientation.HORIZONTAL
        onItemSelectionListener = { clothingSizeTv.text = it.title }

        val clothingSizePickerItems = generateClothingSizePickerItems()
        items = clothingSizePickerItems
        setSelectedItem(clothingSizePickerItems[2])
    }


    private fun generateClothingSizePickerItems(): List<Item> {
        return ClothingSize.values().map {
            PickerItem(
                id = it.ordinal,
                title = it.name
            )
        }
    }


}