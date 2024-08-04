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

import androidx.navigation.fragment.findNavController
import com.paulrybitskyi.commons.utils.viewBinding
import com.paulrybitskyi.valuepicker.sample.BaseFragment
import com.paulrybitskyi.valuepicker.sample.R
import com.paulrybitskyi.valuepicker.sample.databinding.FragmentDashboardBinding

internal class DashboardFragment : BaseFragment<
    FragmentDashboardBinding,
>(R.layout.fragment_dashboard) {

    override val viewBinding by viewBinding(FragmentDashboardBinding::bind)

    override fun onInit() {
        super.onInit()

        initRecyclerView()
    }

    private fun initRecyclerView() = with(viewBinding.recyclerView) {
        adapter = initAdapter()
    }

    private fun initAdapter(): DashboardRecyclerViewAdapter {
        return DashboardRecyclerViewAdapter(
            context = requireContext(),
            items = DashboardDestination.values().toList(),
            onItemClickListener = ::navigateToDestination,
        )
    }

    private fun navigateToDestination(destination: DashboardDestination) {
        findNavController().navigate(destination.destinationId)
    }
}
