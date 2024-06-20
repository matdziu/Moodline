plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.compose.library)
}

android {
    namespace = "com.stats"
}

dependencies {
    implementation(projects.designSystem)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.navigation)

    testImplementation(libs.junit)
}