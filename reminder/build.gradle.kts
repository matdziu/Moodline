plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.compose.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.reminder"
}

dependencies {
    implementation(projects.designSystem)
    implementation(projects.domain)
    implementation(projects.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.lifecycle)
    implementation(libs.work.manager)
    implementation(libs.work.manager.ktx)

    testImplementation(libs.junit)
}