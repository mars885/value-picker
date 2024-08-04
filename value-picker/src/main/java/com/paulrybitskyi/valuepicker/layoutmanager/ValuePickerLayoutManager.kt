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

package com.paulrybitskyi.valuepicker.layoutmanager

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.valuepicker.model.Orientation
import com.paulrybitskyi.valuepicker.model.rvOrientation
import com.paulrybitskyi.valuepicker.valueeffects.ValueEffect
import kotlin.math.abs

internal class ValuePickerLayoutManager(
    private val recyclerView: RecyclerView,
    private val orientation: Orientation,
    private val valueItemEffect: ValueEffect,
) : LinearLayoutManager(
    recyclerView.context,
    orientation.rvOrientation,
    false,
) {

    private val dimension: Int
        get() = when (orientation) {
            Orientation.HORIZONTAL -> width
            Orientation.VERTICAL -> height
        }

    var onViewSelectedListener: ((View) -> Unit)? = null

    override fun onLayoutChildren(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
    ) {
        super.onLayoutChildren(recycler, state)

        applyEffectToChildren()
    }

    private fun applyEffectToChildren() {
        for (i in 0 until childCount) {
            getChildAt(i)?.also { child ->
                valueItemEffect.applyEffect(child, recyclerView, orientation)
            }
        }
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
            .also { applyEffectToChildren() }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
    ): Int {
        return super.scrollHorizontallyBy(dx, recycler, state)
            .also { applyEffectToChildren() }
    }

    override fun onScrollStateChanged(state: Int) {
        reportViewSelectionEvent(state)
    }

    private fun reportViewSelectionEvent(state: Int) {
        if (state != RecyclerView.SCROLL_STATE_IDLE) return

        val recyclerViewCenter = calculateRecyclerViewCenter()
        var minDistance = dimension
        var selectedChild: View? = null

        for (i in 0 until childCount) {
            val child = (getChildAt(i) ?: continue)
            val childCenter = calculateChildCenter(child)
            val childDistanceFromRvCenter = abs(childCenter - recyclerViewCenter)

            if (childDistanceFromRvCenter < minDistance) {
                minDistance = childDistanceFromRvCenter
                selectedChild = child
            }
        }

        selectedChild?.let { onViewSelectedListener?.invoke(it) }
    }

    private fun calculateRecyclerViewCenter(): Int {
        return (dimension / 2)
    }

    private fun calculateChildCenter(child: View): Int {
        return when (orientation) {
            Orientation.VERTICAL -> ((child.height / 2) + child.top)
            Orientation.HORIZONTAL -> ((child.width / 2) + child.left)
        }
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int,
    ) {
        attachCustomSmoothScroller(recyclerView, position)
    }

    private fun attachCustomSmoothScroller(
        recyclerView: RecyclerView,
        position: Int,
    ) {
        ValuePickerSmoothScroller(recyclerView.context)
            .apply { targetPosition = position }
            .also(::startSmoothScroll)
    }
}
