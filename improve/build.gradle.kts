plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.compose.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.improve"
}

dependencies {
    implementation(projects.designSystem)
    implementation(projects.common)
    implementation(projects.analytics)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.icons.extended)

    testImplementation(libs.junit)
}