package com.moviecatalog.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class KtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            extensions.configure<KtlintExtension>("ktlint") {
                filter {
                    exclude("**/generated/**")
                    exclude("**/build/**")
                }
            }
        }
    }
}
