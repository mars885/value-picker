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

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    detekt()
    ktlint()
    gradleVersions()
    dokka()
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(deps.plugins.androidGradle)
        classpath(deps.plugins.kotlinGradle)
        classpath(deps.plugins.gradleVersions)
        classpath(deps.plugins.dokka)
    }
}

detekt {
    parallel = true
    buildUponDefaultConfig = true
    config = files("config/detekt/detekt.yml")
}

tasks.withType<Detekt>().configureEach {
    reports.html.required.set(true)
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        listOf("alpha", "beta", "rc").any { keyword ->
            candidate.version.lowercase().contains(keyword)
        }
    }
}

allprojects {
    apply(plugin = PLUGIN_DETEKT)
    apply(plugin = PLUGIN_KTLINT)

    repositories {
        mavenCentral()
        google()
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set(versions.ktlint)
        android.set(true)
        outputToConsole.set(true)
        verbose.set(true)
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
        }
    }
}

val clean by tasks.registering(Delete::class) {
    delete(buildDir)
}
