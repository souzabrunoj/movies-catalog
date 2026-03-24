import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.platform.detekt)
    alias(libs.plugins.platform.ktlint)
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.moviecatalog.features.home.generated.resources"
    }
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    android {
        namespace = "com.moviecatalog.features.home"
        compileSdk = 36
        minSdk = 24
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        androidResources {
            enable = true
        }
    }

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.designSystem)
            implementation(projects.core.navigator)
            implementation(projects.core.uiModel)

            implementation(libs.compose.runtime)
            implementation(libs.compose.animation)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)

            implementation(libs.lifecycle.runtime.compose)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.material.icons.core)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
        }
        androidMain.dependencies {
            implementation(libs.androidx.compose.ui.tooling.preview)
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
            implementation(projects.core.tests)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.androidx.compose.ui.tooling)
}
