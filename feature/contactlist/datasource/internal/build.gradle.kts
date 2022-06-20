import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.Libraries.AndroidX.Room
import com.picpay.desafio.android.Modules
import com.picpay.desafio.android.config.AppConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {
    kapt(Room.compiler)
    implementation(Room.ktx)
    implementation(Room.runtime)
    implementation(Libraries.ThirdParty.Koin.core)
    implementation(Libraries.ThirdParty.Koin.android)

    implementation(project(Modules.contactListUseCase))
    implementation(project(Modules.contactListRepository))

    testImplementation(Libraries.AndroidX.Tests.jUnit)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Google.Truth.truth)
    testImplementation(Libraries.ThirdParty.Mockk.mockk)
    testImplementation(Libraries.AndroidX.Tests.extjUnit)

    androidTestImplementation(Libraries.AndroidX.Tests.runner)
    androidTestImplementation(Libraries.AndroidX.Tests.extjUnit)
    androidTestImplementation(Libraries.Google.Truth.truth)
    androidTestImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    androidTestImplementation(Libraries.AndroidX.Tests.archCoreTesting)
    androidTestImplementation(Libraries.AndroidX.Tests.core)
    androidTestImplementation(Libraries.AndroidX.Tests.jUnit)
}