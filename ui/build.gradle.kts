import com.picpay.desafio.android.Libraries.AndroidX.Compose
import com.picpay.desafio.android.config.AppConfig
import com.picpay.desafio.android.Libraries.ThirdParty.Koin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    implementation(Compose.material)
    implementation(Compose.animation)
    implementation(Compose.uiTooling)
    implementation(Koin.core)
}