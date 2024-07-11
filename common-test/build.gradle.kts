plugins {
    alias(libs.plugins.moodline.android.library)
}

android {
    namespace = "com.commontest"
}

dependencies {
    implementation(projects.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.junit)
    implementation(libs.kotlin.coroutines.test)
}