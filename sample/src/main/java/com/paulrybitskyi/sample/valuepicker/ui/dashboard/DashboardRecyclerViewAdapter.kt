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

package com.paulrybitskyi.sample.valuepicker.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulrybitskyi.sample.valuepicker.R
import com.paulrybitskyi.sample.valuepicker.ui.dashboard.DashboardRecyclerViewAdapter.ViewHolder

internal class DashboardRecyclerViewAdapter(
    context: Context,
    private val items: List<DashboardDestination>,
    private val onItemClickListener: (DashboardDestination) -> Unit
): RecyclerView.Adapter<ViewHolder>() {


    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_view_dashboard,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        val item = items[position]

        title.text = item.title
        description.text = item.description

        itemView.setOnClickListener {
            onItemClickListener(item)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        internal val title = itemView.findViewById<TextView>(R.id.titleTv)
        internal val description = itemView.findViewById<TextView>(R.id.descriptionTv)

    }


}