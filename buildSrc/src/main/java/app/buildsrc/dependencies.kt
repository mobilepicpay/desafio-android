package app.buildsrc

object Versions {
    const val compileSdkVersion = 31
    const val targetSdkVersion = 30
    const val minSdkVersion = 24
    const val buildToolsVersion = "30.0.2"
}

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0"

    object Kotlin {
        private const val version = "1.4.20"
        const val stdlibjdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.1.0"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.1"

        object Lifecycle {
            private const val version = "2.1.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val support = "androidx.legacy:legacy-support-v4:1.0.0"
        }
    }

    object Coroutines {
        private const val version = "1.5.0"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Koin {
        private const val version = "2.0.1"
        const val core = "org.koin:koin-core:$version"
        const val android = "org.koin:koin-android:$version"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
        const val test = "org.koin:koin-test:$version"
    }

    object Room {
        private const val version = "2.4.1"
        const val core = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val mockk = "io.mockk:mockk:1.10.6"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:3.14.1"
        const val arch = "androidx.arch.core:core-testing:2.1.0"
        const val testRunner = "androidx.test:runner:$1.1.1"
        const val espresso = "androidx.test.espresso:espresso-core:$3.1.1"
        const val coreKtxTest = "androidx.test:core-ktx:$1.4.0"
    }

    const val gson = "com.google.code.gson:gson:2.8.5"
    const val picasso = "com.squareup.picasso:picasso:2.71828"
    const val circleimageview = "de.hdodenhof:circleimageview:3.0.0"
    const val logInterceptor = "com.squareup.okhttp3:logging-interceptor:4.2.1"

}