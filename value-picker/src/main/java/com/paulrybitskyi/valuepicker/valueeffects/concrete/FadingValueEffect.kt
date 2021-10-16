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

package com.paulrybitskyi.valuepicker.valueeffects.concrete

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.valueeffects.ValueEffect
import kotlin.math.abs

/**
 * An implementation of the value effect that modifies child views'
 * alpha property to create a fading effect.
 *
 * see [ValueEffect]
 */
class FadingValueEffect : ValueEffect {


    override fun applyEffect(child: View, recyclerView: RecyclerView, orientation: Orientation) {
        val rvCenter = recyclerView.calculateCenter(orientation)
        val childCenter = child.calculateChildCenter(orientation)
        val rvDistanceFrmChildCenter = abs(rvCenter - childCenter)
        val alpha = calculateChildAlpha(rvDistanceFrmChildCenter, recyclerView.getDimension(orientation))

        child.alpha = alpha
    }


    private fun RecyclerView.calculateCenter(orientation: Orientation): Float {
        return (getDimension(orientation) / 2f)
    }


    private fun RecyclerView.getDimension(orientation: Orientation): Int {
        return when(orientation) {
            Orientation.VERTICAL -> height
            Orientation.HORIZONTAL -> width
        }
    }


    private fun View.calculateChildCenter(orientation: Orientation): Int {
        return when(orientation) {
            Orientation.VERTICAL -> ((height / 2) + top)
            Orientation.HORIZONTAL -> ((width / 2) + left)
        }
    }


    private fun calculateChildAlpha(rvDistanceFrmChildCenter: Float, rvDimension: Int): Float {
        return (1 - (rvDistanceFrmChildCenter / (rvDimension / 2f)))
    }


}
