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

package com.paulrybitskyi.valuepicker.sample.ratingpicker

import android.graphics.Typeface
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.utils.viewBinding
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem
import com.paulrybitskyi.valuepicker.sample.BaseFragment
import com.paulrybitskyi.valuepicker.sample.R
import com.paulrybitskyi.valuepicker.sample.databinding.FragmentRatingPickerBinding
import com.paulrybitskyi.valuepicker.sample.ratingpicker.model.Rating

internal class RatingPickerFragment : BaseFragment<
    FragmentRatingPickerBinding
>(R.layout.fragment_rating_picker) {


    override val viewBinding by viewBinding(FragmentRatingPickerBinding::bind)


    override fun onInit() {
        super.onInit()

        initRatingPicker()
    }


    private fun initRatingPicker() = with(viewBinding.ratingPicker) {
        areDividersEnabled = true
        isInfiniteScrollEnabled = false
        maxVisibleItems = 3
        textSize = getDimension(R.dimen.rating_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        dividerColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.DEFAULT_BOLD
        orientation = Orientation.HORIZONTAL
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            viewBinding.ratingTv.text = (it.payload as Rating).title
        }

        val ratingPickerItems = generateRatingPickerItems()
        items = ratingPickerItems
        setSelectedItem(ratingPickerItems[2])
    }


    private fun generateRatingPickerItems(): List<Item> {
        return Rating.values().map {
            PickerItem(
                id = it.ordinal,
                title = it.number.toString(),
                payload = it
            )
        }
    }


}