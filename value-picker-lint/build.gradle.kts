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

plugins {
    `java-library`
    kotlin()
    kotlinKapt()
    lint()
}

java.sourceCompatibility = appConfig.javaCompatibilityVersion
java.targetCompatibility = appConfig.javaCompatibilityVersion

dependencies {
    compileOnly(deps.lintApi)

    // See this for why stdLib is needed:
    // https://pspdfkit.com/blog/2020/how-updating-to-kotlin-14-broke-our-linter-rules/
    compileOnly(deps.stdLib)

    compileOnly(deps.autoService)
    kapt(deps.autoService)

    testImplementation(deps.lintTests)
}