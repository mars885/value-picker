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

package com.paulrybitskyi.valuepicker.sample.dashboard

import androidx.annotation.IdRes
import com.paulrybitskyi.valuepicker.sample.R

internal enum class DashboardDestination(
    val title: String,
    val description: String,
    @IdRes val destinationId: Int
) {

    TIME_PICKER(
        title = "Time Picker",
        description = "Three vertical pickers for picking time.",
        destinationId = R.id.timePickerFragment
    ),
    DATE_PICKER(
        title = "Date Picker",
        description = "Three vertical pickers with infinite scroll enabled for picking date.",
        destinationId = R.id.datePickerFragment
    ),
    TEAM_PICKER(
        title = "Team Picker",
        description = "A vertical picker with a fixed item size, custom divider, and text font for picking the NBA team.",
        destinationId = R.id.teamPickerFragment
    ),
    MOVIE_FILTERING_PICKER(
        title = "Movie Filtering Picker",
        description = "Three vertical pickers each with a custom effect for picking a movie.",
        destinationId = R.id.movieFilteringPicker
    ),
    RATING_PICKER(
        title = "Rating Picker",
        description = "A horizontal picker for picking a rating.",
        destinationId = R.id.ratingPickerFragment
    ),
    CLOTHING_SIZE_PICKER(
        title = "Clothing Size Picker",
        description = "A horizontal picker with a fixed item size, custom divider, and infinite scroll enabled for picking the clothing size.",
        destinationId = R.id.clothingSizePickerFragment
    ),
    PERSON_INFO_PICKER(
        title = "Person Info Picker",
        description = "Three horizontal pickers each with a custom effect for picking person's information.",
        destinationId = R.id.personInfoPickerFragment
    )

}