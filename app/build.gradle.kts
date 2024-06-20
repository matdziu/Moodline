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
    implementation(projects.diary)
    implementation(projects.stats)
    implementation(projects.improve)
    implementation(projects.addEntry)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.icons.extended)

    testImplementation(libs.junit)
}