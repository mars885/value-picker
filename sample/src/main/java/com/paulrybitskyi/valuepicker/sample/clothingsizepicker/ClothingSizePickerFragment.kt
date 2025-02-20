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

package com.paulrybitskyi.valuepicker.sample.clothingsizepicker

import android.graphics.Typeface
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.commons.ktx.getDrawable
import com.paulrybitskyi.commons.utils.viewBinding
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem
import com.paulrybitskyi.valuepicker.model.Size
import com.paulrybitskyi.valuepicker.sample.BaseFragment
import com.paulrybitskyi.valuepicker.sample.R
import com.paulrybitskyi.valuepicker.sample.clothingsizepicker.model.ClothingSize
import com.paulrybitskyi.valuepicker.sample.databinding.FragmentClothingSizePickerBinding

@Suppress("MagicNumber")
internal class ClothingSizePickerFragment : BaseFragment<
    FragmentClothingSizePickerBinding,
>(R.layout.fragment_clothing_size_picker) {

    override val viewBinding by viewBinding(FragmentClothingSizePickerBinding::bind)

    override fun onInit() {
        super.onInit()

        initClothingSizePicker()
    }

    private fun initClothingSizePicker() = with(viewBinding.clothingSizePicker) {
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        maxVisibleItems = 3
        textSize = getDimension(R.dimen.clothing_size_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.DEFAULT_BOLD
        dividerDrawable = getDrawable(R.drawable.clothing_size_picker_divider)
        fixedItemSize = Size.withFixedSize(
            width = getDimensionPixelSize(R.dimen.clothing_size_picker_item_width),
            height = getDimensionPixelSize(R.dimen.clothing_size_picker_item_height),
        )
        orientation = Orientation.HORIZONTAL
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            viewBinding.clothingSizeTv.text = it.title
        }

        val clothingSizePickerItems = generateClothingSizePickerItems()
        items = clothingSizePickerItems
        setSelectedItem(clothingSizePickerItems[2])
    }

    private fun generateClothingSizePickerItems(): List<Item> {
        return ClothingSize.entries.map {
            PickerItem(
                id = it.ordinal,
                title = it.name,
            )
        }
    }
}
