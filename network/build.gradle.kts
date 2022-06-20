import com.picpay.desafio.android.config.AppConfig.BuildConfigField
import com.picpay.desafio.android.Libraries.ThirdParty
import com.picpay.desafio.android.config.AppConfig

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        buildConfigField(
            name = BuildConfigField.BASE_URL.name,
            value = BuildConfigField.BASE_URL.value,
            type = BuildConfigField.BASE_URL.type
        )
    }

}

dependencies {
    implementation(ThirdParty.Koin.core)
    implementation(ThirdParty.Retrofit.retrofit)
    implementation(ThirdParty.Retrofit.gsonConverter)
    implementation(ThirdParty.Okhttp.okhttp)
    implementation(ThirdParty.Okhttp.loggingInterceptor)
}