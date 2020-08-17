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

package com.paulrybitskyi.sample.valuepicker.ui.ratingpicker.model

internal enum class Rating(
    val number: Int,
    val title: String
) {

    ONE(
        number = 1,
        title = "One"
    ),
    TWO(
        number = 2,
        title = "Two"
    ),
    THREE(
        number = 3,
        title = "Three"
    ),
    FOUR(
        number = 4,
        title = "Four"
    ),
    FIVE(
        number = 5,
        title = "Five"
    ),
    SIX(
        number = 6,
        title = "Six"
    ),
    SEVEN(
        number = 7,
        title = "Seven"
    ),
    EIGHT(
        number = 8,
        title = "Eight"
    ),
    NINE(
        number = 9,
        title = "Nine"
    ),
    TEN(
        number = 10,
        title = "Ten"
    )

}