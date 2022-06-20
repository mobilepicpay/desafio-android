import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.config.AppConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.compileSdk
}

dependencies {
    implementation(Libraries.Jetbrains.KotlinX.coroutines)
    implementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    implementation(Libraries.AndroidX.Tests.jUnit)
}