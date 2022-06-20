package com.picpay.desafio.android.config

import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 28

    const val applicationId = "com.picpay.desafio.android"

    object Version {
        const val major = 0
        const val minor = 2
        const val patch = 0
        const val build = 0
    }

    const val versionCode =
        Version.major * 1000000 + Version.minor * 10000 + Version.patch * 100 + Version.build

    const val versionName =
        "${Version.major}.${Version.minor}.${Version.patch}-${Version.build}"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"

    var baseName = "vibra-pos-$versionName"

    object CompileOptions {
        val javaSourceCompatibility = JavaVersion.VERSION_1_8
        const val kotlinJvmTarget = "1.8"
    }

    object ComposeOptions {
        const val kotlinCompilerExtensionVersion = "1.1.1"
    }

    object BuildConfigField {
        val BASE_URL = ConfigField(
            type = "String",
            name = "API_BASE_URL",
            value = "\"https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/\""
        )
    }
}
