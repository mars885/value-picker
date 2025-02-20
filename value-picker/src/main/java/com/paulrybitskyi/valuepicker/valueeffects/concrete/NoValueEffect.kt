/*
 * Copyright 2020 Paul Rybitskyi, oss@paulrybitskyi.com
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

/**
 * An implementation of the value effect that does nothing.
 *
 * see [ValueEffect]
 */
class NoValueEffect : ValueEffect {

    override fun applyEffect(child: View, recyclerView: RecyclerView, orientation: Orientation) {
        // Stub
    }
}
