import com.picpay.desafio.android.Modules

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(project(Modules.contactListUseCase))
}