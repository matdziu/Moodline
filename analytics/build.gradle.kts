plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.analytics"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.facebook.sdk)

    testImplementation(libs.junit)
}