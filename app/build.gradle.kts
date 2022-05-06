plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "br.com.alaksion.qrcodereader"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
//            shrinkResources = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-network"))

    // AndroidX
    implementation(libs.androidX.core.ktx)
    implementation(libs.androidX.lifecycle.ktx)
    implementation(libs.androidX.splashscreen)

    // Coroutines
    implementation(libs.coroutines.core)

    // Hilt
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)

    // Compose
    implementation(libs.compose.activty)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.accompanist.insets)

}

kapt {
    correctErrorTypes = true
}
