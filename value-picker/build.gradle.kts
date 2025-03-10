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

plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        namespace = "com.paulrybitskyi.valuepicker"
        minSdk = appConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = appConfig.javaCompatibilityVersion
        targetCompatibility = appConfig.javaCompatibilityVersion
    }

    kotlinOptions {
        jvmTarget = appConfig.kotlinCompatibilityVersion.toString()
    }
}

dependencies {
    api(deps.recyclerView)

    implementation(deps.appCompat)
    implementation(deps.coreKtx)
    implementation(deps.commonsCore)
    implementation(deps.commonsKtx)
    implementation(deps.commonsRecyclerView)

    lintPublish(project(deps.local.valuePickerLint))

    testImplementation(deps.jUnit)
    androidTestImplementation(deps.jUnitExt)
}

apply(from = "../publishing.gradle.kts")
