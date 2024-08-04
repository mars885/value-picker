/*
 * Copyright 2021 Paul Rybitskyi, paul.rybitskyi.work@gmail.com
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

import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestFiles.xml
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

@Suppress("MaxLineLength")
internal class NumberPickerUsageDetectorTest {

    @Test
    fun `Detects NumberPicker simple name usage in XML layout file`() {
        lint()
            .files(
                xml(
                    "res/layout/test.xml",
                    """
                    <NumberPicker
                        xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"/>
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                res/layout/test.xml:1: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                <NumberPicker
                 ~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                Fix for res/layout/test.xml line 1: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                @@ -1 +1
                - <NumberPicker
                + <com.paulrybitskyi.valuepicker.ValuePickerView
                """,
            )
    }

    @Test
    fun `Detects NumberPicker canonical name usage in XML layout file`() {
        lint()
            .files(
                xml(
                    "res/layout/test.xml",
                    """
                    <android.widget.NumberPicker
                        xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"/>
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                res/layout/test.xml:1: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                <android.widget.NumberPicker
                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                Fix for res/layout/test.xml line 1: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                @@ -1 +1
                - <android.widget.NumberPicker
                + <com.paulrybitskyi.valuepicker.ValuePickerView
                """,
            )
    }

    @Test
    fun `Does not detect NumberPicker usage in XML layout file`() {
        lint()
            .files(
                xml(
                    "res/layout/test.xml",
                    """
                    <Picker
                        xmlns:android="http://schemas.android.com/apk/res/android"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"/>
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun `Detects imported NumberPicker instantiation using 1st constructor in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;
                    import android.widget.NumberPicker;

                    public class NumberPickerFactory {

                        public NumberPicker create(Context context) {
                            return new NumberPicker(context);
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.java:9: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return new NumberPicker(context);
                               ~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                   Fix for src/test/pkg/NumberPickerFactory.java line 9: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                   @@ -9 +9
                   -         return new NumberPicker(context);
                   +         return new com.paulrybitskyi.valuepicker.ValuePickerView(context);
                """,
            )
    }

    @Test
    fun `Detects imported NumberPicker instantiation using 2nd constructor in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;
                    import android.util.AttributeSet;
                    import android.widget.NumberPicker;

                    public class NumberPickerFactory {

                        public NumberPicker create(Context context, AttributeSet attrs) {
                            return new NumberPicker(context, attrs);
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.java:10: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return new NumberPicker(context, attrs);
                               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                   Fix for src/test/pkg/NumberPickerFactory.java line 10: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                   @@ -10 +10
                   -         return new NumberPicker(context, attrs);
                   +         return new com.paulrybitskyi.valuepicker.ValuePickerView(context, attrs);
                """,
            )
    }

    @Test
    fun `Detects imported NumberPicker instantiation using 3rd constructor in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;
                    import android.util.AttributeSet;
                    import android.widget.NumberPicker;

                    public class NumberPickerFactory {

                        public NumberPicker create(Context context, AttributeSet attrs, int defStyleAttr) {
                            return new NumberPicker(context, attrs, defStyleAttr);
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.java:10: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return new NumberPicker(context, attrs, defStyleAttr);
                               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                   Fix for src/test/pkg/NumberPickerFactory.java line 10: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                   @@ -10 +10
                   -         return new NumberPicker(context, attrs, defStyleAttr);
                   +         return new com.paulrybitskyi.valuepicker.ValuePickerView(context, attrs, defStyleAttr);
                """,
            )
    }

    @Test
    fun `Detects imported NumberPicker instantiation using 4th constructor in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;
                    import android.util.AttributeSet;
                    import android.widget.NumberPicker;

                    public class NumberPickerFactory {

                        public NumberPicker create(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
                            return new NumberPicker(context, attrs, defStyleAttr, defStyleRes);
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.java:10: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return new NumberPicker(context, attrs, defStyleAttr, defStyleRes);
                               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
    }

    @Test
    fun `Detects fully qualified NumberPicker instantiation in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;

                    public class NumberPickerFactory {

                        public android.widget.NumberPicker create(Context context) {
                            return new android.widget.NumberPicker(context);
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.java:8: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return new android.widget.NumberPicker(context);
                               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                   Fix for src/test/pkg/NumberPickerFactory.java line 8: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                   @@ -8 +8
                   -         return new android.widget.NumberPicker(context);
                   +         return new com.paulrybitskyi.valuepicker.ValuePickerView(context);
                """,
            )
    }

    @Test
    fun `Does not detect NumberPicker instantiation in Java file`() {
        lint()
            .files(
                java(
                    """
                    package test.pkg;

                    import android.content.Context;
                    import android.widget.NumberPicker;

                    public class NumberPickerFactory {

                        public NumberPicker create(Context context) {
                            return null;
                        }
                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun `Detects NumberPicker instantiation in Kotlin file`() {
        lint()
            .files(
                kotlin(
                    """
                    package test.pkg

                    import android.content.Context
                    import android.widget.NumberPicker

                    object NumberPickerFactory {

                        fun create(context: Context): NumberPicker {
                            return NumberPicker(context)
                        }

                    }
                    """,
                ).indented(),
            )
            .issues(NumberPickerUsageDetector.ISSUE)
            .run()
            .expect(
                """
                src/test/pkg/NumberPickerFactory.kt:9: Warning: Using 'NumberPicker' instead of 'ValuePickerView' [NumberPickerUsage]
                        return NumberPicker(context)
                               ~~~~~~~~~~~~~~~~~~~~~
                0 errors, 1 warnings
                """,
            )
            .expectFixDiffs(
                """
                   Fix for src/test/pkg/NumberPickerFactory.kt line 9: Replace with com.paulrybitskyi.valuepicker.ValuePickerView:
                   @@ -9 +9
                   -         return NumberPicker(context)
                   +         return com.paulrybitskyi.valuepicker.ValuePickerView(context)
                """,
            )
    }
}
