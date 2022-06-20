import com.picpay.desafio.android.Libraries
import com.picpay.desafio.android.Libraries.ThirdParty.Koin
import com.picpay.desafio.android.Libraries.ThirdParty.Retrofit
import com.picpay.desafio.android.Modules
import com.picpay.desafio.android.extensions.testImplementation

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(Koin.core)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConverter)

    testImplementation(Retrofit.gsonConverter)
    testImplementation(Libraries.AndroidX.Tests.jUnit)
    testImplementation(Libraries.Jetbrains.KotlinX.Tests.coroutines)
    testImplementation(Libraries.AndroidX.Tests.runner)
    testImplementation(Libraries.Google.Truth.truth)
    testImplementation(Libraries.ThirdParty.Mockk.mockk)
    testImplementation(Libraries.ThirdParty.Mockk.mockkWebService)

    implementation(project(Modules.contactListUseCase))
    implementation(project(Modules.contactListRepository))
}