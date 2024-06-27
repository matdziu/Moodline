plugins {
    alias(libs.plugins.moodline.android.library)
}

android {
    namespace = "com.common"
}

dependencies {
    implementation(projects.designSystem)
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
}