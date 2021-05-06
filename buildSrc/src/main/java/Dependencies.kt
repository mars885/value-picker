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

@file:Suppress("ClassName")

import org.gradle.api.JavaVersion


object appConfig {

    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val minSdkVersion = 21
    const val buildToolsVersion = "30.0.0"
    const val applicationId = "com.paulrybitskyi.valuepicker.sample"

    val javaCompatibilityVersion = JavaVersion.VERSION_1_8
    val kotlinCompatibilityVersion = JavaVersion.VERSION_1_8

}


object publishingConfig {

    const val artifactGroupId = "com.paulrybitskyi.valuepicker"
    const val artifactWebsite = "https://github.com/mars885/value-picker"

    const val mavenPublicationName = "release"

    const val licenseName = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val developerId = "mars885"
    const val developerName = "Paul Rybitskyi"
    const val developerEmail = "paul.rybitskyi.work@gmail.com"
    const val siteUrl = "https://github.com/mars885/value-picker"
    const val gitUrl = "https://github.com/mars885/value-picker.git"

    const val hostRepoName = "sonatype"
    const val hostRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"

    const val artifactName = "valuepicker"
    const val artifactVersion = "1.0.0"
    const val artifactDescription = "An Android library that provides a simple and customizable ValuePicker."

}


object versions {

    const val kotlin = "1.4.32" // also in buildSrc build.gradle.kts file
    const val gradlePlugin = "4.1.3" // also in buildSrc build.gradle.kts file and lint version
    const val gradleVersionsPlugin = "0.38.0"
    const val dokkaPlugin = "1.4.32"
    const val appCompat = "1.2.0"
    const val recyclerView = "1.2.0"
    const val navFragmentKtx = "2.3.5"
    const val navUiKtx = "2.3.5"
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.3.2"
    const val commonsCore = "1.0.2"
    const val commonsKtx = "1.0.2"
    const val commonsRecyclerView = "1.0.0"
    const val autoService = "1.0"
    const val lint = "27.1.3"   // lintVersion = gradlePluginVersion + 23.0.0
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.2"

}


object deps {

    object plugins {

        const val androidGradle = "com.android.tools.build:gradle:${versions.gradlePlugin}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${versions.gradleVersionsPlugin}"
        const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${versions.dokkaPlugin}"

    }

    object local {

        const val valuePicker = ":value-picker"
        const val valuePickerLint = ":value-picker-lint"

    }

    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${versions.appCompat}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${versions.recyclerView}"
    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${versions.navFragmentKtx}"
    const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${versions.navUiKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${versions.coreKtx}"
    const val commonsCore = "com.paulrybitskyi.commons:commons-core:${versions.commonsCore}"
    const val commonsKtx = "com.paulrybitskyi.commons:commons-ktx:${versions.commonsKtx}"
    const val commonsRecyclerView = "com.paulrybitskyi.commons:commons-recyclerview:${versions.commonsRecyclerView}"
    const val autoService = "com.google.auto.service:auto-service:${versions.autoService}"
    const val lintApi = "com.android.tools.lint:lint-api:${versions.lint}"
    const val lintTests = "com.android.tools.lint:lint-tests:${versions.lint}"
    const val jUnit = "junit:junit:${versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${versions.jUnitExt}"

}