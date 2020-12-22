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

package com.paulrybitskyi.sample.valuepicker.timepicker

import android.graphics.Typeface
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.utils.viewBinding
import com.paulrybitskyi.sample.valuepicker.BaseFragment
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.databinding.FragmentTimePickerBinding
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.ValuePickerView.OnItemSelectedListener
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem

internal class TimePickerFragment : BaseFragment<
    FragmentTimePickerBinding
>(R.layout.fragment_time_picker) {


    override val viewBinding by viewBinding(FragmentTimePickerBinding::bind)


    override fun onInit() {
        super.onInit()

        initPickers()
    }


    private fun initPickers() {
        initHourPicker()
        initMinutePicker()
        initPeriodPicker()
    }


    private fun ValuePickerView.initPicker() {
        areDividersEnabled = true
        isInfiniteScrollEnabled = false
        maxVisibleItems = 3
        textColor = getColor(R.color.colorAccent)
        dividerColor = getColor(R.color.colorAccent)
        textSize = getDimension(R.dimen.time_picker_text_size)
        textTypeface = Typeface.DEFAULT_BOLD
        orientation = Orientation.VERTICAL
    }


    private fun initHourPicker() = with(viewBinding.hourPicker) {
        initPicker()
        onItemSelectedListener = OnItemSelectedListener{
            viewBinding.hourTv.text = "Hour: ${it.title}"
        }

        val hourPickerItems = generateHourPickerItems()
        items = hourPickerItems
        setSelectedItem(hourPickerItems[10])
    }


    private fun generateHourPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(hour in 1..12) {
                add(
                    PickerItem(
                        id = hour,
                        title = hour.toString()
                    )
                )
            }
        }
    }


    private fun initMinutePicker() = with(viewBinding.minutePicker) {
        initPicker()
        onItemSelectedListener = OnItemSelectedListener {
            viewBinding.minuteTv.text = "Minute: ${it.title}"
        }

        val minutePickerItems = generateMinutePickerItems()
        items = minutePickerItems
        setSelectedItem(minutePickerItems[30])
    }


    private fun generateMinutePickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(minute in 0..59) {
                add(
                    PickerItem(
                        id = minute,
                        title = if (minute < 10) "0$minute" else minute.toString()
                    )
                )
            }
        }
    }


    private fun initPeriodPicker() = with(viewBinding.periodPicker) {
        initPicker()
        onItemSelectedListener = OnItemSelectedListener {
            viewBinding.periodTv.text = "Period: ${it.title}"
        }

        val periodPickerItems = generatePeriodPickerItems()
        items = periodPickerItems
        setSelectedItem(periodPickerItems[1])
    }


    private fun generatePeriodPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            add(
                PickerItem(
                    id = 1,
                    title = "AM"
                )
            )
            add(
                PickerItem(
                    id = 2,
                    title = "PM"
                )
            )
        }
    }


}