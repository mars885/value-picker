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

package com.paulrybitskyi.valuepicker.valueeffects

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.valueeffects.concrete.FadingValueEffect
import com.paulrybitskyi.valuepicker.valueeffects.concrete.NoValueEffect

/**
 * An interface responsible for applying cool visual effects to
 * child views of the value picker.
 */
interface ValueEffect {

    /**
     * Applies visual effect to child views of the value picker.
     *
     * @param child The child view of the value picker
     * @param recyclerView The recycler view of the value picker
     * @param orientation The current orientation
     *
     * see [FadingValueEffect]
     * see [NoValueEffect]
     */
    fun applyEffect(child: View, recyclerView: RecyclerView, orientation: Orientation)
}
