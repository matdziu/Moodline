import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.moodline.android.application)
    alias(libs.plugins.moodline.android.hilt)
    alias(libs.plugins.moodline.android.compose.application)
}

android {
    namespace = "com.moodline"

    defaultConfig {
        applicationId = "com.moodline"
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {

            val keystorePropertiesFile = rootProject.file("keystore.properties")
            val keystoreProperties = Properties()

            keystoreProperties.load(FileInputStream(keystorePropertiesFile))

            keyAlias = keystoreProperties.getProperty("MOODLINE_UPLOAD_KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("MOODLINE_UPLOAD_KEY_PASSWORD")
            storeFile = file(keystoreProperties.getProperty("MOODLINE_UPLOAD_KEYSTORE_PATH"))
            storePassword = keystoreProperties.getProperty("MOODLINE_UPLOAD_STORE_PASSWORD")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}

dependencies {
    implementation(projects.designSystem)
    implementation(projects.diary)
    implementation(projects.stats)
    implementation(projects.improve)
    implementation(projects.addEntry)
    implementation(projects.storage)
    implementation(projects.common)
    implementation(projects.analytics)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.icons.extended)
    implementation(libs.androidx.compose.lifecycle)

    testImplementation(projects.commonTest)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.turbine)
}