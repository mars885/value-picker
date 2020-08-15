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

package com.paulrybitskyi.sample.valuepicker

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.commons.ktx.showShortToast
import com.paulrybitskyi.valuepicker.Item
import com.paulrybitskyi.valuepicker.Size
import com.paulrybitskyi.valuepicker.ValuePickerView
import kotlinx.android.synthetic.main.activity_main.*

internal class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPickers()
        initInfoContainer()
    }


    private fun initPickers() {
        initTimePicker()
        initDatePicker()
        initTeamPicker()
    }


    private fun initTimePicker() {
        initHourPicker()
        initMinutePicker()
        initPeriodPicker()
    }


    private fun ValuePickerView.initAsTimePicker() {
        maxVisibleItems = 3
        textSize = getDimension(R.dimen.time_picker_text_size)
        textColor = getColor(R.color.time_picker_text_color)
        dividerColor = getColor(R.color.time_picker_divider_color)
        typeface = Typeface.DEFAULT_BOLD
        areDividersEnabled = true
        isInfiniteScrollEnabled = false
    }


    private fun initHourPicker() = with(hourPicker) {
        initAsTimePicker()
        onItemSelectionListener = { hourTv.text = "Hour: ${it.title}" }

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


    private fun initMinutePicker() = with(minutePicker) {
        initAsTimePicker()
        onItemSelectionListener = { minuteTv.text = "Minute: ${it.title}" }

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
                        title = if(minute < 10) "0$minute" else minute.toString()
                    )
                )
            }
        }
    }


    private fun initPeriodPicker() = with(periodPicker) {
        initAsTimePicker()
        onItemSelectionListener = { periodTv.text = "Period: ${it.title}" }

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


    private fun initDatePicker() {
        initMonthPicker()
        initDayPicker()
        initYearPicker()
    }


    private fun ValuePickerView.initAsDatePicker() {
        maxVisibleItems = 5
        textSize = getDimension(R.dimen.date_picker_text_size)
        textColor = getColor(R.color.date_picker_text_color)
        dividerColor = getColor(R.color.date_picker_divider_color)
        typeface = Typeface.SANS_SERIF
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
    }


    private fun initMonthPicker() = with(monthPicker) {
        initAsDatePicker()
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
        initAsDatePicker()
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
        initAsDatePicker()
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


    private fun initTeamPicker() = with(teamPicker) {
        maxVisibleItems = 7
        textSize = getDimension(R.dimen.team_picker_text_size)
        textColor = getColor(R.color.team_picker_text_color)
        typeface = Typeface.SANS_SERIF
        dividerDrawable = getDrawable(R.drawable.team_picker_divider_drawable)
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        fixedItemSize = Size.withFixedSize(
            width = getDimensionPixelSize(R.dimen.team_picker_item_width),
            height = getDimensionPixelSize(R.dimen.team_picker_item_height)
        )
        onItemSelectionListener = { teamTv.text = "Team: ${(it.payload as Team).shortName}" }

        val teamPickerItems = generateTeamPickerItems()
        items = teamPickerItems
        setSelectedItem(teamPickerItems[4])
    }


    private fun generateTeamPickerItems(): List<Item> {
        return Team.values().map {
            PickerItem(
                id = it.ordinal,
                title = it.longName,
                payload = it
            )
        }
    }


    private fun initInfoContainer() {
        initPickedTimeTitle()
        initPickedDateTitle()
        initPickedTeamTitle()
    }


    private fun initPickedTimeTitle() {
        pickedTimeTv.setOnClickListener {
            showShortToast(constructPickedTimeTitle())
        }
    }


    private fun constructPickedTimeTitle(): String {
        return buildString {
            append("Hour: ${hourPicker.selectedItem?.title}, ")
            append("Minute: ${minutePicker.selectedItem?.title}, ")
            append("Period: ${periodPicker.selectedItem?.title}.")
        }
    }


    private fun initPickedDateTitle() {
        pickedDateTv.setOnClickListener {
            showShortToast(constructPickedDateTitle())
        }
    }


    private fun constructPickedDateTitle(): String {
        return buildString {
            append("Month: ${monthPicker.selectedItem?.title}, ")
            append("Day: ${dayPicker.selectedItem?.title}, ")
            append("Year: ${yearPicker.selectedItem?.title}.")
        }
    }


    private fun initPickedTeamTitle() {
        pickedTeamTv.setOnClickListener {
            showShortToast(constructPickedTeamTitle())
        }
    }


    private fun constructPickedTeamTitle(): String {
        return "Team: ${teamPicker.selectedItem?.title}"
    }


    private class PickerItem(
        override val id: Int,
        override val title: String,
        override val payload: Any? = null
    ) : Item


    enum class Month(
        val longName: String,
        val shortName: String
    ) {

        JANUARY(longName = "January", shortName = "Jan"),
        FEBRUARY(longName = "February", shortName = "Feb"),
        MARCH(longName = "March", shortName = "Mar"),
        APRIL(longName = "April", shortName = "Apr"),
        MAY(longName = "May", shortName = "May"),
        JUNE(longName = "June", shortName = "Jun"),
        JULY(longName = "July", shortName = "Jul"),
        AUGUST(longName = "August", shortName = "Aug"),
        SEPTEMBER(longName = "September", shortName = "Sept"),
        OCTOBER(longName = "October", shortName = "Oct"),
        NOVEMBER(longName = "November", shortName = "Nov"),
        DECEMBER(longName = "December", shortName = "Dec")

    }


    enum class Team(
        val longName: String,
        val shortName: String
    ) {

        BOSTON_CELTICS(longName = "Boston Celtics", shortName = "BC"),
        MINNESOTA_TIMBERWOLVES(longName = "Minnesota Timberwolves", shortName = "MT"),
        OKLAHOMA_CITY_THUNDER(longName = "Oklahoma City Thunder", shortName = "OCT"),
        HOUSTON_ROCKETS(longName = "Houston Rockets", shortName = "HR"),
        ORLANDO_MAGIC(longName = "Orlando Magic", shortName = "OM"),
        CHICAGO_BULLS(longName = "Chicago Bulls", shortName = "CB"),
        TORONTO_RAPTORS(longName = "Toronto Raptors", shortName = "TR"),
        NEW_YORK_KNICKS(longName = "New York Knics", shortName = "NYK"),
        INDIANA_PACERS(longName = "Indiana Pacers", shortName = "IP"),
        MIAMI_HEAT(longName = "Miami Heat", shortName = "MH"),
        GOLDEN_STATE_WARRIOS(longName = "Golden State Warriors", shortName = "GSW"),
        LOS_ANGELES_LAKERS(longName = "Los Angeles Lakers", shortName = "LAL"),
        SAN_ANTONIO_SPURS(longName = "San Antonio Spurs", shortName = "SAS")

    }


}