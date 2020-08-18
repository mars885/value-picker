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

package com.paulrybitskyi.sample.valuepicker.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paulrybitskyi.sample.valuepicker.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

internal class DashboardFragment : Fragment(R.layout.fragment_dashboard) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }


    private fun initRecyclerView() = with(recyclerView) {
        adapter = initAdapter()
    }


    private fun initAdapter(): DashboardRecyclerViewAdapter {
        return DashboardRecyclerViewAdapter(
            context = requireContext(),
            items = DashboardDestination.values().toList(),
            onItemClickListener = ::navigateToDestination
        )
    }


    private fun navigateToDestination(destination: DashboardDestination) {
        findNavController().navigate(destination.destinationId)
    }


}