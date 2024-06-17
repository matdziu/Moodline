plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.compose.library)
}

android {
    namespace = "com.designsystem"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit)
}