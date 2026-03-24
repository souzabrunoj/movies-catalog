import org.gradle.api.JavaVersion
import org.gradle.api.tasks.testing.Test

plugins {
    id("com.android.library")
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.platform.detekt)
    alias(libs.plugins.platform.ktlint)
}

android {
    namespace = "com.moviecatalog.core.uitests"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

tasks.withType<Test>().configureEach {
    reports.html.required.set(false)
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material3.android)
    testImplementation(libs.paparazzi.runtime)
    testImplementation(libs.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
