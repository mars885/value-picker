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

package com.paulrybitskyi.sample.valuepicker.teampicker

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.paulrybitskyi.commons.ktx.getColor
import com.paulrybitskyi.commons.ktx.getDimension
import com.paulrybitskyi.commons.ktx.getDimensionPixelSize
import com.paulrybitskyi.commons.ktx.getDrawable
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.teampicker.model.Team
import com.paulrybitskyi.valuepicker.ValuePickerView
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.PickerItem
import com.paulrybitskyi.valuepicker.model.Size
import kotlinx.android.synthetic.main.fragment_team_picker.*

internal class TeamPickerFragment : Fragment(R.layout.fragment_team_picker) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTeamPicker()
    }


    private fun initTeamPicker() = with(teamPicker) {
        areDividersEnabled = true
        isInfiniteScrollEnabled = false
        maxVisibleItems = 5
        textSize = getDimension(R.dimen.team_picker_text_size)
        textColor = getColor(R.color.colorAccent)
        textTypeface = (ResourcesCompat.getFont(context, R.font.ubuntu_mono_bold) ?: Typeface.SANS_SERIF)
        dividerDrawable = getDrawable(R.drawable.team_picker_divider)
        fixedItemSize = Size.withFixedSize(
            width = getDimensionPixelSize(R.dimen.team_picker_item_width),
            height = getDimensionPixelSize(R.dimen.team_picker_item_height)
        )
        orientation = Orientation.VERTICAL
        onItemSelectedListener = ValuePickerView.OnItemSelectedListener {
            teamTv.text = (it.payload as Team).longName
        }

        val teamPickerItems = generateTeamPickerItems()
        items = teamPickerItems
        setSelectedItem(teamPickerItems[2])
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


}