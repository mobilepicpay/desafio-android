import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.Modules
import com.picpay.desafio.android.config.AppConfig


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        applicationId = AppConfig.applicationId

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        setProperty("archivesBaseName", AppConfig.baseName)
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "consumer-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.ComposeOptions.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(project(Modules.ui))
    implementation(project(Modules.network))
    implementation(project(Modules.contactListUseCaseImpl))
    implementation(project(Modules.contactListUseCase))
    implementation(project(Modules.contactListRepository))
    implementation(project(Modules.contactListRepositoryImpl))
    implementation(project(Modules.contactListRemoteDataSource))
    implementation(project(Modules.contactListInternalDataSource))
    implementation(project(Modules.contactListPresentation))
    implementation(Libraries.ThirdParty.Koin.core)
    implementation(Libraries.ThirdParty.Koin.compose)
    implementation(Libraries.AndroidX.Compose.activity)
    implementation(Libraries.AndroidX.Compose.material)
    implementation(Libraries.AndroidX.Compose.viewModelLifecycle)

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