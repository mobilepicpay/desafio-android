import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.Libraries.ThirdParty.Koin
import com.picpay.desafio.android.Modules

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(project(Modules.contactListUseCase))
    implementation(project(Modules.contactListRepository))
    implementation(Koin.core)

    testImplementation(Libraries.AndroidX.Tests.jUnit)
    testImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Google.Truth.truth)
    testImplementation(Libraries.ThirdParty.Mockk.mockk)

}