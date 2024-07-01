plugins {
    alias(libs.plugins.moodline.android.library)
    alias(libs.plugins.moodline.android.hilt)
}

android {
    namespace = "com.storage"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
}