package app.buildsrc

object Versions {
    const val compileSdkVersion = 30
    const val targetSdkVersion = 30
    const val minSdkVersion = 22
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
        const val multidex = "androidx.multidex:multidex:2.0.1"
        const val browser = "androidx.browser:browser:1.0.0"
        const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
        const val workRuntime = "androidx.work:work-runtime-ktx:2.6.0"

        object Lifecycle {
            private const val version = "2.1.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val support = "androidx.legacy:legacy-support-v4:1.0.0"
        }

        object Navigation {
            private const val version = "2.3.1"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Fragment {
            private const val version = "1.2.0"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

    }

    object Coroutines {
        private const val version = "1.3.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0"
    }

    object Retrofit {
        private const val version = "2.5.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Koin {
        private const val version = "2.0.1"
        const val core = "org.koin:koin-core:$version"
        const val android = "org.koin:koin-android:$version"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"
        const val test = "org.koin:koin-test:$version"
    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.13"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val mockk = "io.mockk:mockk:1.10.6"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:3.14.1"
        const val arch = "android.arch.core:core-testing:1.1.1"
    }

    const val gson = "com.google.code.gson:gson:2.8.5"
    const val picasso = "com.squareup.picasso:picasso:2.71828"
    const val circleimageview = "de.hdodenhof:circleimageview:3.0.0"

}