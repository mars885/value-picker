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

package com.paulrybitskyi.valuepicker.sample.valueeffects

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.valueeffects.ValueEffect

private const val PROPERTY_ROTATION_X_START_ANGLE = 90f
private const val PROPERTY_ROTATION_Y_START_ANGLE = -90f

internal class RotationValueEffect(
    private val property: Property,
) : ValueEffect {

    override fun applyEffect(child: View, recyclerView: RecyclerView, orientation: Orientation) {
        val rvCenter = recyclerView.calculateCenter(orientation)
        val childCenter = child.calculateChildCenter(orientation)
        val rvDistanceFrmChildCenter = (rvCenter - childCenter)
        val rotation = calculateChildRotation(
            rvDistanceFrmChildCenter,
            recyclerView.getDimension(orientation),
        )

        when (property) {
            Property.ROTATION_X -> child.rotationX = rotation
            Property.ROTATION_Y -> child.rotationY = rotation
        }
    }

    private fun calculateChildRotation(rvDistanceFrmChildCenter: Float, rvDimension: Int): Float {
        return (property.starAngle * (rvDistanceFrmChildCenter / (rvDimension / 2f)))
    }

    private val Property.starAngle: Float
        get() = when (this) {
            Property.ROTATION_X -> PROPERTY_ROTATION_X_START_ANGLE
            Property.ROTATION_Y -> PROPERTY_ROTATION_Y_START_ANGLE
        }

    internal enum class Property {
        ROTATION_X,
        ROTATION_Y,
    }
}
