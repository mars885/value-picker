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

package com.paulrybitskyi.sample.valuepicker.moviefilteringpicker

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.moviefilteringpicker.model.Genre
import com.paulrybitskyi.sample.valuepicker.moviefilteringpicker.model.StreamingService
import com.paulrybitskyi.sample.valuepicker.valueeffects.CompositeValueEffect
import com.paulrybitskyi.sample.valuepicker.valueeffects.RotationValueEffect
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.ValuePickerView.OnItemSelectedListener
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem
import com.paulrybitskyi.valuepicker.valueeffects.concrete.FadingValueEffect
import kotlinx.android.synthetic.main.fragment_movie_filtering_picker.*

internal class MovieFilteringPickerFragment : Fragment(R.layout.fragment_movie_filtering_picker) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPickers()
    }


    private fun initPickers() {
        initGenrePicker()
        initYearPicker()
        initServicePicker()
    }


    private fun ValuePickerView.initMovieFilteringPicker() {
        areDividersEnabled = true
        isInfiniteScrollEnabled = true
        maxVisibleItems = 5
        textSize = getDimension(R.dimen.movie_filtering_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        dividerColor = getColor(R.color.colorAccent)
        textTypeface = Typeface.SANS_SERIF
        orientation = Orientation.VERTICAL
    }


    private fun initGenrePicker() = with(genrePicker) {
        initMovieFilteringPicker()
        valueEffect = RotationValueEffect(RotationValueEffect.Property.ROTATION_X)
        onItemSelectedListener = OnItemSelectedListener {
            genreTv.text = "Genre: ${it.title}"
        }

        val genrePickerItems = generateGenrePickerItems()
        items = genrePickerItems
        setSelectedItem(genrePickerItems[3])
    }


    private fun generateGenrePickerItems(): List<Item> {
        return Genre.values().map {
            PickerItem(
                id = it.ordinal,
                title = it.title,
                payload = it
            )
        }
    }


    private fun initYearPicker() = with(yearPicker) {
        initMovieFilteringPicker()
        valueEffect = RotationValueEffect(RotationValueEffect.Property.ROTATION_Y)
        onItemSelectedListener = OnItemSelectedListener {
            yearTv.text = "Year: ${it.title}"
        }

        val yearPickerItems = generateYearPickerItems()
        items = yearPickerItems
        setSelectedItem(yearPickerItems.last())
    }


    private fun generateYearPickerItems(): List<Item> {
        return mutableListOf<Item>().apply {
            for(year in 1930..2020) {
                add(
                    PickerItem(
                        id = year,
                        title = year.toString()
                    )
                )
            }
        }
    }


    private fun initServicePicker() = with(servicePicker) {
        initMovieFilteringPicker()
        valueEffect = CompositeValueEffect(listOf(
            FadingValueEffect(),
            RotationValueEffect(RotationValueEffect.Property.ROTATION_X)
        ))
        onItemSelectedListener = OnItemSelectedListener {
            serviceTv.text = "Service: ${it.title}"
        }

        val servicePickerItems = generateServicePickerItems()
        items = servicePickerItems
        setSelectedItem(servicePickerItems[0])
    }


    private fun generateServicePickerItems(): List<Item> {
        return StreamingService.values().map {
            PickerItem(
                id = it.ordinal,
                title = it.title,
                payload = it
            )
        }
    }


}