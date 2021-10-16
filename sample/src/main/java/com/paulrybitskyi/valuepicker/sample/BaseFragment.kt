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

package com.paulrybitskyi.valuepicker.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

internal abstract class BaseFragment<
    VB : ViewBinding
>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {


    private var isViewCreated = false

    protected abstract val viewBinding: VB


    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Prevent the view from recreation until onDestroy is called
        return if(isViewCreated) {
            viewBinding.root
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }


    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wasViewCreated = isViewCreated
        isViewCreated = true

        if(!wasViewCreated) {
            onInit()
        }
    }


    @CallSuper
    protected open fun onInit() {
        // Stub
    }


    override fun onDestroy() {
        super.onDestroy()

        isViewCreated = false
    }


}
