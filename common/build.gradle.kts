plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}