import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.platform.detekt)
    alias(libs.plugins.platform.ktlint)
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.moviecatalog.generated.resources"
    }
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    android {
        namespace = "com.moviecatalog.library"
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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        commonMain.dependencies {
            implementation(projects.core.navigator)
            implementation(projects.core.designSystem)
            implementation(projects.core.uiModel)

            implementation(libs.compose.runtime)
            implementation(libs.compose.animation)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)

            implementation(libs.lifecycle.runtime.compose)
            implementation(libs.material.icons.core)

            implementation(libs.coil.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
        }
        iosMain.dependencies {
            implementation(projects.features.login)
            implementation(projects.features.home)
            implementation(libs.koin.core)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.androidx.compose.ui.tooling)
}
