plugins {
    alias(libs.plugins.moodline.android.library)
}

android {
    namespace = "com.designsystem"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
}