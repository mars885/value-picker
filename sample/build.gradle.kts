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
    androidApplication()
    kotlinAndroid()
}

android {
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        applicationId = appConfig.applicationId
        namespace = appConfig.applicationId
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
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
    implementation(project(deps.local.valuePicker))

    implementation(deps.appCompat)
    implementation(deps.navFragmentKtx)
    implementation(deps.navUiKtx)
    implementation(deps.constraintLayout)
    implementation(deps.commonsCore)
    implementation(deps.commonsKtx)

    testImplementation(deps.jUnit)
    androidTestImplementation(deps.jUnitExt)
}

val installGitHook by tasks.registering(Copy::class) {
    from(File(rootProject.rootDir, "hooks/pre-push"))
    into(File(rootProject.rootDir, ".git/hooks/"))
    // https://github.com/gradle/kotlin-dsl-samples/issues/1412
    fileMode = 0b111101101 // -rwxr-xr-x
}

tasks.getByPath(":sample:preBuild").dependsOn(installGitHook)
