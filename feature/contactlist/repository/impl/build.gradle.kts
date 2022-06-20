import com.picpay.desafio.android.Modules
import com.picpay.desafio.android.Libraries.ThirdParty.Koin
import com.picpay.desafio.android.extensions.testImplementation
import com.picpay.desafio.android.Libraries

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(Koin.core)
    implementation(project(Modules.contactListRepository))
    implementation(project(Modules.contactListUseCase))

    testImplementation(Libraries.AndroidX.Tests.jUnit)
    testImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Google.Truth.truth)
    testImplementation(Libraries.ThirdParty.Mockk.mockk)
}