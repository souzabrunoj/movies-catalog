import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.platform.detekt)
    alias(libs.plugins.platform.ktlint)
}

kotlin {
    target {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    dependencies {
        implementation(projects.core.navigator)
        implementation(projects.composeApp)
        implementation(projects.features.login)
        implementation(projects.features.home)
        implementation(libs.koin.android)
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.core.splashscreen)
        implementation(libs.androidx.compose.ui.tooling.preview)
    }
}

android {
    namespace = "com.moviecatalog"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.moviecatalog"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.androidx.compose.ui.tooling)
}