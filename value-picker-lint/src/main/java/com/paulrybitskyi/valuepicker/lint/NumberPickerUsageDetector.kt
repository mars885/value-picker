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

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScanner
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.getQualifiedName
import org.w3c.dom.Element
import java.util.EnumSet

internal class NumberPickerUsageDetector : Detector(), XmlScanner, SourceCodeScanner {

    companion object {

        @JvmField
        val ISSUE = Issue.create(
            id = "NumberPickerUsage",
            briefDescription = NUMBER_PICKER_USAGE_BRIEF_DESC,
            explanation = """
                Since `ValuePickerView` is included in the project, it is likely
                that **it** should be used instead of `NumberPicker`.
            """,
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.WARNING,
            implementation = Implementation(
                NumberPickerUsageDetector::class.java,
                EnumSet.of(Scope.RESOURCE_FILE, Scope.JAVA_FILE),
                Scope.RESOURCE_FILE_SCOPE,
                Scope.JAVA_FILE_SCOPE,
            ),
        )
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return (folderType == ResourceFolderType.LAYOUT)
    }

    override fun run(context: Context) {
        // The infrastructure should never call this method on an xml detector since
        // it will run the various visitors instead

        assert(false)
    }

    override fun getApplicableElements(): Collection<String> {
        return listOf(NUMBER_PICKER_SIMPLE_NAME, NUMBER_PICKER_CANONICAL_NAME)
    }

    // The below handles usages in XML Layout Files

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            issue = ISSUE,
            location = context.getElementLocation(element),
            message = NUMBER_PICKER_USAGE_BRIEF_DESC,
            quickfixData = computeQuickFixForXmlUsage(element),
        )
    }

    private fun computeQuickFixForXmlUsage(element: Element): LintFix {
        return fix()
            .replace().text(element.tagName)
            .with(VALUE_PICKER_VIEW_CANONICAL_NAME)
            .build()
    }

    // The below handles usages in Java/Kotlin Source Files

    override fun getApplicableConstructorTypes(): List<String> {
        return listOf(NUMBER_PICKER_CANONICAL_NAME)
    }

    override fun visitConstructor(
        context: JavaContext,
        node: UCallExpression,
        constructor: PsiMethod,
    ) {
        context.report(
            issue = ISSUE,
            location = context.getLocation(node),
            message = NUMBER_PICKER_USAGE_BRIEF_DESC,
            quickfixData = computeQuickFixForSourceUsage(node),
        )
    }

    private fun computeQuickFixForSourceUsage(node: UCallExpression): LintFix? {
        // ValuePickerView does not support 4 arg constructor
        @Suppress("MagicNumber")
        if (node.valueArgumentCount == 4) return null

        val textToReplace = if (node.asSourceString().contains(NUMBER_PICKER_CANONICAL_NAME)) {
            node.classReference.getQualifiedName()
        } else {
            node.classReference!!.resolvedName
        }

        return fix()
            .replace().text(textToReplace)
            .with(VALUE_PICKER_VIEW_CANONICAL_NAME)
            .shortenNames()
            .reformat(true)
            .build()
    }
}
