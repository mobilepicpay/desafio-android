plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.library)
    }
    id("kotlin-parcelize")
}

android {
    namespace = libs.versions.appId.get() + ".core.network"
    compileSdk = libs.versions.androidApiCompile.get().toInt()

    defaultConfig {
        buildFeatures.buildConfig = true
        minSdk = libs.versions.androidApiMin.get().toInt()
        buildConfigField("String", "BASE_URL", libs.versions.baseUrl.get())
        testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
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
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:testing"))

    api(libs.retrofit)
    api(libs.retrofitConverterGson)
    api(libs.okhttp)
    api(libs.okhttp.interceptor)
    api(libs.okhttpMockwebserver)
}