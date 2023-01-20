import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import config.ConfigData
import dependencies.Dependencies

plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
}

val baseUrl: String = gradleLocalProperties(rootDir).getProperty("BASE_URL")
android {
    namespace = ConfigData.namespace
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.namespace
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
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

    testOptions {
        unitTests.isReturnDefaultValues = true
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
    implementation(Dependencies.espressoIdling)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.testArch)
    testImplementation(Dependencies.testKoin)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.roomTest)

    androidTestImplementation(Dependencies.testRunner)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(Dependencies.espressoIdling)
    androidTestImplementation(Dependencies.testCore)
    androidTestImplementation(Dependencies.testArch)
    androidTestImplementation(Dependencies.testKoin)
    androidTestImplementation(Dependencies.coroutinesTest)
    androidTestImplementation(Dependencies.androidJunit)

    kapt(Dependencies.roomCompiler)
}
