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

package com.paulrybitskyi.valuepicker.sample.personinfopicker

import android.graphics.Typeface
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.commons.utils.viewBinding
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem
import com.paulrybitskyi.valuepicker.model.Size
import com.paulrybitskyi.valuepicker.sample.BaseFragment
import com.paulrybitskyi.valuepicker.sample.R
import com.paulrybitskyi.valuepicker.sample.databinding.FragmentPersonInfoPickerBinding
import com.paulrybitskyi.valuepicker.sample.valueeffects.CompositeValueEffect
import com.paulrybitskyi.valuepicker.sample.valueeffects.RotationValueEffect
import com.paulrybitskyi.valuepicker.valueeffects.concrete.FadingValueEffect
import com.paulrybitskyi.valuepicker.valueeffects.concrete.NoValueEffect

@Suppress("MagicNumber")
internal class PersonInfoPickerFragment : BaseFragment<
    FragmentPersonInfoPickerBinding,
>(R.layout.fragment_person_info_picker) {

    override val viewBinding by viewBinding(FragmentPersonInfoPickerBinding::bind)

    override fun onInit() {
        super.onInit()

        initPickers()
    }

    private fun initPickers() {
        initAgePicker()
        initHeightPicker()
        initWeightPicker()
    }

    private fun ValuePickerView.initPersonInfoPicker() {
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        maxVisibleItems = 3
        textSize = getDimension(R.dimen.person_info_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        dividerColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.DEFAULT_BOLD
        fixedItemSize = Size.withFixedSize(
            width = getDimensionPixelSize(R.dimen.person_info_picker_item_width),
            height = getDimensionPixelSize(R.dimen.person_info_picker_item_height),
        )
        orientation = Orientation.HORIZONTAL
    }

    private fun initAgePicker() = with(viewBinding.agePicker) {
        initPersonInfoPicker()
        valueEffect = RotationValueEffect(RotationValueEffect.Property.ROTATION_Y)
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            viewBinding.ageTv.text = "Age: ${it.title}"
        }

        val agePickerItems = generateAgePickerItems()
        items = agePickerItems
        setSelectedItem(agePickerItems[19])
    }

    private fun generateAgePickerItems(): List<Item> {
        return buildList {
            for (age in 1..100) {
                add(
                    PickerItem(
                        id = age,
                        title = age.toString(),
                    ),
                )
            }
        }
    }

    private fun initHeightPicker() = with(viewBinding.heightPicker) {
        initPersonInfoPicker()
        valueEffect = NoValueEffect()
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            viewBinding.heightTv.text = "Height: ${it.title}"
        }

        val heightPickerItems = generateHeightPickerItems()
        items = heightPickerItems
        setSelectedItem(heightPickerItems[130])
    }

    private fun generateHeightPickerItems(): List<Item> {
        return buildList {
            for (cm in 50..250) {
                add(
                    PickerItem(
                        id = cm,
                        title = "$cm cm",
                    ),
                )
            }
        }
    }

    private fun initWeightPicker() = with(viewBinding.weightPicker) {
        initPersonInfoPicker()
        valueEffect = CompositeValueEffect(
            listOf(
                FadingValueEffect(),
                RotationValueEffect(RotationValueEffect.Property.ROTATION_Y),
            ),
        )
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            viewBinding.weightTv.text = "Weight: ${it.title}"
        }

        val weightPickerItems = generateWeightPickerItems()
        items = weightPickerItems
        setSelectedItem(weightPickerItems[60])
    }

    private fun generateWeightPickerItems(): List<Item> {
        return buildList {
            for (kg in 10..150) {
                add(
                    PickerItem(
                        id = kg,
                        title = "$kg kg",
                    ),
                )
            }
        }
    }
}
