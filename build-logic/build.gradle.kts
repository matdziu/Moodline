import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    implementation(libs.kotlin.gradle)
    implementation(libs.android.gradle)
    implementation(libs.ksp.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.moodline.convention.plugins.android.application"
            implementationClass = "com.buildlogic.plugins.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.moodline.convention.plugins.android.library"
            implementationClass = "com.buildlogic.plugins.AndroidLibraryConventionPlugin"
        }

        register("androidHilt") {
            id = "com.moodline.convention.plugins.android.hilt"
            implementationClass = "com.buildlogic.plugins.AndroidHiltConventionPlugin"
        }

        register("androidComposeApplication") {
            id = "com.moodline.convention.plugins.android.compose.application"
            implementationClass = "com.buildlogic.plugins.AndroidComposeApplicationConventionPlugin"
        }

        register("androidComposeLibrary") {
            id = "com.moodline.convention.plugins.android.compose.library"
            implementationClass = "com.buildlogic.plugins.AndroidComposeLibraryConventionPlugin"
        }
    }
}