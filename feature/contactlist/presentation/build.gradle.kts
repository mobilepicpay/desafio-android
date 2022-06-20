import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.Modules
import com.picpay.desafio.android.config.AppConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = AppConfig.compileSdk

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.ComposeOptions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(Libraries.AndroidX.Compose.activity)
    implementation(Libraries.AndroidX.Compose.material)
    implementation(Libraries.AndroidX.Compose.uiTooling)
    implementation(project(Modules.ui))
    implementation(project(Modules.contactListUseCase))
    implementation(project(Modules.testLib))
    implementation(Libraries.ThirdParty.coil)
    implementation(Libraries.ThirdParty.Koin.core)
    implementation(Libraries.ThirdParty.Koin.android)
    implementation(Libraries.ThirdParty.Koin.compose)

    testImplementation(Libraries.AndroidX.Tests.jUnit)
    testImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Google.Truth.truth)
    testImplementation(Libraries.AndroidX.Compose.jUnit)
    testImplementation(Libraries.ThirdParty.Mockk.mockk)
    testImplementation(Libraries.ThirdParty.Koin.jUnit)
    testImplementation(Libraries.ThirdParty.UiTest.roboletric)
    debugImplementation(Libraries.AndroidX.Compose.testManifest)
}