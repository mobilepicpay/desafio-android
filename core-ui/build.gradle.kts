plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt") version libs.versions.detekt.get()
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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

    implementation( libs.androidX.core.ktx)
    api (libs.bundles.commom.ui)
    api (libs.bundles.compose)
    api (libs.compose.constraintlayout)

}

detekt {
    parallel = true
    config = files("$rootDir/config/detekt/detekt.yml")
    baseline = file("$rootDir/config/detekt/baseline.xml")
}