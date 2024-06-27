plugins {
    alias(libs.plugins.moodline.android.library)
}

android {
    namespace = "com.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}