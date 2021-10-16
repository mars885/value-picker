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

package com.paulrybitskyi.valuepicker

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.commons.ktx.views.setTextSizeInPx
import com.paulrybitskyi.commons.utils.observeChanges
import com.paulrybitskyi.valuepicker.ValuePickerRecyclerViewAdapter.ViewHolder
import com.paulrybitskyi.valuepicker.model.Item
import com.paulrybitskyi.valuepicker.model.ValueItemConfig
import com.paulrybitskyi.valuepicker.scrollerhelpers.ScrollerHelper

internal class ValuePickerRecyclerViewAdapter(
    items: List<Item>,
    var valueItemConfig: ValueItemConfig,
    var scrollerHelper: ScrollerHelper
) : RecyclerView.Adapter<ViewHolder>() {

    var items by observeChanges(items) { _, newItems ->
        scrollerHelper.dataSetItemCount = newItems.size
        notifyDataSetChanged()
    }

    var onItemClickListener: ((View) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createTextView(parent.context))
    }

    private fun createTextView(context: Context): TextView {
        return AppCompatTextView(context).apply {
            layoutParams = RecyclerView.LayoutParams(
                valueItemConfig.size.width,
                valueItemConfig.size.height
            )
            gravity = Gravity.CENTER
            setTextColor(valueItemConfig.textColor)
            setTextSizeInPx(valueItemConfig.textSize)
            typeface = valueItemConfig.textTypeface
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, adapterPosition: Int) = with(holder.valueTv) {
        text = items[scrollerHelper.calculateDataSetPosition(adapterPosition)].title
        setOnClickListener(onItemClickListener)
    }

    override fun getItemCount(): Int {
        return scrollerHelper.adapterItemCount
    }

    fun getItemAdapterPosition(item: Item): Int? {
        return items.indexOfFirst { it.id == item.id }
            .takeIf { it != -1 }
            ?.let { dataSetPosition ->
                scrollerHelper.calculateAdapterPosition(dataSetPosition)
            }
    }

    fun getItem(adapterPosition: Int): Item? {
        return items.getOrNull(scrollerHelper.calculateDataSetPosition(adapterPosition))
    }

    class ViewHolder(val valueTv: TextView) : RecyclerView.ViewHolder(valueTv)
}
