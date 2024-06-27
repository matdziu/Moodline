plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.storage"
}

dependencies {
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
}