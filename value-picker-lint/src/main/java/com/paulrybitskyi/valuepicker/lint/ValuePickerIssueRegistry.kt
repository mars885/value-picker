/*
 * Copyright 2021 Paul Rybitskyi, oss@paulrybitskyi.com
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

package com.paulrybitskyi.valuepicker.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.google.auto.service.AutoService

private val VENDOR = Vendor(
    vendorName = "ValuePicker Android Library",
    feedbackUrl = "https://github.com/mars885/value-picker/issues/new",
)

@AutoService(IssueRegistry::class)
internal class ValuePickerIssueRegistry : IssueRegistry() {
    override val issues = listOf(NumberPickerUsageDetector.ISSUE)
    override val api = CURRENT_API
    override val vendor = VENDOR
}
