plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.library)
    }
}

android {
    namespace = libs.versions.appId.get() + ".core.testing"
    compileSdk = libs.versions.androidApiCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()

        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()

        jvmTarget = libs.versions.java.get()

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {

    api(libs.junit)
    api(libs.mockk)
    api(libs.mockitoCore)
    api(libs.mockitoKotlin)
    api(libs.coreTesting)

    api(libs.androidx.test.core)
    api(libs.androidx.test.coreKtx)

    api(libs.androidx.junit)
    api(libs.androidx.ui.test.junit4.android)
    api(libs.androidx.ui.test.manifest)
    api(libs.androidx.navigation.testing)

    api(libs.kotlinx.coroutines.test)

    api(libs.koin.android.test)
    api(libs.koin.android.testJU4)
    api(libs.koin.androidx.compose)
    api(libs.koin.core)

    api(libs.testRunner)

    androidTestImplementation(project(":core:network"))
    androidTestImplementation(project(":core:common"))
    androidTestImplementation(project(":core:domain"))
    androidTestImplementation(project(":core:data"))

}