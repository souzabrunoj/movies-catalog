package com.moviecatalog.convention

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            extensions.configure<DetektExtension>("detekt") {
                parallel = true
                buildUponDefaultConfig = true
                allRules = false
                config.setFrom(rootProject.files("config/detekt/detekt.yml"))
            }
            tasks.withType<Detekt>().configureEach {
                jvmTarget = "11"
                exclude("**/build/**")
                reports {
                    sarif.required.set(true)
                }
            }
        }
    }
}
