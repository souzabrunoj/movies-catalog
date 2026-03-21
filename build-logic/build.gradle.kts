plugins {
    `kotlin-dsl`
}

group = "com.moviecatalog.buildlogic"

dependencies {
    implementation(libs.detekt.gradle.plugin)
    implementation(libs.ktlint.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("detektConvention") {
            id = "com.moviecatalog.convention.detekt"
            implementationClass = "com.moviecatalog.convention.DetektConventionPlugin"
            version = "1.0.0"
        }
        register("ktlintConvention") {
            id = "com.moviecatalog.convention.ktlint"
            implementationClass = "com.moviecatalog.convention.KtlintConventionPlugin"
            version = "1.0.0"
        }
    }
}
