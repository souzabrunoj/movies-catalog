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

        implementation(libs.koin.core)
        implementation(libs.androidx.activity.compose)
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

/**
 * Copies `ic_launcher.png` / `ic_launcher_round.png` from an Android mipmap export into this module's `res/`.
 * Destination is **movie-catalog** `:androidApp` only.
 *
 * Usage:
 *   ./gradlew :androidApp:importAndroidLauncherIcons
 *   ./gradlew :androidApp:importAndroidLauncherIcons -PlauncherIconsSource=/path/to/Android
 */
tasks.register<Copy>("importAndroidLauncherIcons") {
    description =
        "Copy launcher mipmaps from Downloads export (or -PlauncherIconsSource) into src/main/res"
    group = "build setup"
    val home = System.getProperty("user.home")
    val fromPath =
        (project.findProperty("launcherIconsSource") as String?)
            ?: "$home/Downloads/AppAssets_2026-03-21/Android"
    val srcDir = file(fromPath)
    from(srcDir) {
        include(
            "mipmap-mdpi/ic_launcher.png",
            "mipmap-mdpi/ic_launcher_round.png",
            "mipmap-hdpi/ic_launcher.png",
            "mipmap-hdpi/ic_launcher_round.png",
            "mipmap-xhdpi/ic_launcher.png",
            "mipmap-xhdpi/ic_launcher_round.png",
            "mipmap-xxhdpi/ic_launcher.png",
            "mipmap-xxhdpi/ic_launcher_round.png",
            "mipmap-xxxhdpi/ic_launcher.png",
            "mipmap-xxxhdpi/ic_launcher_round.png",
        )
    }
    into(layout.projectDirectory.dir("src/main/res"))
    doFirst {
        require(srcDir.isDirectory) {
            "Missing launcher export at $srcDir. Pass -PlauncherIconsSource=/path/to/folder containing mipmap-*"
        }
    }
}
