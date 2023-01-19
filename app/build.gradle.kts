import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import dependencies.Dependencies

plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
}

val baseUrl: String = gradleLocalProperties(rootDir).getProperty("BASE_URL")
android {
    namespace = config.ConfigData.namespace
    compileSdk = config.ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = config.ConfigData.namespace
        minSdk = config.ConfigData.minSdkVersion
        targetSdk = config.ConfigData.targetSdkVersion
        versionCode = config.ConfigData.versionCode
        versionName = config.ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"${baseUrl}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures.viewBinding = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.core)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.materialDesign)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinCore)

    implementation(Dependencies.viewModel)
    implementation(Dependencies.livedata)
    implementation(Dependencies.runtime)

    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)

    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverter)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.mockWebserver)

    implementation(Dependencies.picasso)
    implementation(Dependencies.circleimageview)

    implementation(Dependencies.room)
    implementation(Dependencies.roomRuntime)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.testArch)
    testImplementation(Dependencies.testKoin)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.roomTest)

    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(Dependencies.testCore)

    kapt(Dependencies.roomCompiler)
}
