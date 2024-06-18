plugins {
    alias(libs.plugins.moodline.android.application)
    alias(libs.plugins.moodline.android.hilt)
    alias(libs.plugins.moodline.android.compose.application)
}

android {
    namespace = "com.moodline"

    defaultConfig {
        applicationId = "com.moodline"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.designSystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.splashscreen)

    testImplementation(libs.junit)
}