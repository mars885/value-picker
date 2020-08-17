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

package com.paulrybitskyi.sample.valuepicker.ui.datepicker

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.sample.valuepicker.PickerItem
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.ui.datepicker.model.Month
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.model.Orientation
import kotlinx.android.synthetic.main.fragment_date_picker.*

internal class DatePickerFragment : Fragment(R.layout.fragment_date_picker) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPickers()
    }


    private fun initPickers() {
        initMonthPicker()
        initDayPicker()
        initYearPicker()
    }


    private fun ValuePickerView.initDatePicker() {
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        maxVisibleItems = 5
        textSize = getDimension(R.dimen.date_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        dividerColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.SANS_SERIF
        orientation = Orientation.VERTICAL
    }


    private fun initMonthPicker() = with(monthPicker) {
        initDatePicker()
        onItemSelectionListener = { monthTv.text = "Month: ${(it.payload as Month).shortName}" }

        val monthPickerItems = generateMonthPickerItems(longMonthNames = true)
        items = monthPickerItems
        setSelectedItem(monthPickerItems[7])
    }


    private fun generateMonthPickerItems(longMonthNames: Boolean): List<Item> {
        return Month.values().map {
            PickerItem(
                id = it.ordinal,
                title = if(longMonthNames) it.longName else it.shortName,
                payload = it
            )
        }
    }


    private fun initDayPicker() = with(dayPicker) {
        initDatePicker()
        onItemSelectionListener = { dayTv.text = "Day: ${it.title}" }

        val dayPickerItems = generateDayPickerItems()
        items = dayPickerItems
        setSelectedItem(dayPickerItems[8])
    }


    private fun generateDayPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(day in 1..31) {
                add(
                    PickerItem(
                        id = day,
                        title = if(day < 10) "0$day" else day.toString()
                    )
                )
            }
        }
    }


    private fun initYearPicker() = with(yearPicker) {
        initDatePicker()
        onItemSelectionListener = { yearTv.text = "Year: ${it.title}" }

        val yearPickerItems = generateYearPickerItems()
        items = yearPickerItems
        setSelectedItem(yearPickerItems[0])
    }


    private fun generateYearPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(year in 2020..2030) {
                add(
                    PickerItem(
                        id = year,
                        title = year.toString()
                    )
                )
            }
        }
    }


}