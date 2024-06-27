plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.compose.library)
}

android {
    namespace = "com.designsystem"
}

dependencies {
    implementation(projects.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.google.fonts)
    implementation(libs.androidx.compose.navigation)

    testImplementation(libs.junit)
}