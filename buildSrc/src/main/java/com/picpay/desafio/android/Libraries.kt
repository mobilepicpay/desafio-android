package com.picpay.desafio.android

object Libraries {

    object Jetbrains {
        object Kotlin {
            const val stdLib =
                "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.JetBrains.Kotlin.stdLib}"
        }

        object KotlinX {
            const val coroutines =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.JetBrains.KotlinX.coroutines}"

            object Tests {
                const val coroutines =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.JetBrains.KotlinX.coroutines}"
            }
        }
    }

    object Google {
        object Truth {
            const val truth = "com.google.truth:truth:${Versions.Google.Truth.truth}"
        }
    }

    object Android {
        const val material =
            "com.google.android.material:material:${Versions.Android.material}"
    }

    object AndroidX {
        const val startup =
            "androidx.startup:startup-runtime:${Versions.AndroidX.startup}"
        const val appCompat =
            "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val coreKtx =
            "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"

        object Navigation {
            const val ui =
                "androidx.navigation:navigation-com.picpay.desafio.contact_list.presentation.ui-ktx:${Versions.AndroidX.Navigation.ui}"
            const val compose =
                "androidx.navigation:navigation-compose:${Versions.AndroidX.Navigation.compose}"
        }

        object Compose {
            const val activity =
                "androidx.activity:activity-compose:${Versions.AndroidX.Compose.activity}"
            const val material =
                "androidx.compose.material:material:${Versions.AndroidX.Compose.material}"
            const val animation =
                "androidx.compose.animation:animation:${Versions.AndroidX.Compose.animation}"
            const val uiTooling =
                "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose.uiTooling}"
            const val viewModelLifecycle =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.Compose.viewModelLifecycle}"
            const val runtimeLiveData =
                "androidx.compose.runtime:runtime-livedata:${Versions.AndroidX.Compose.runtimeLiveData}"
            const val jUnit =
                "androidx.compose.ui:ui-test-junit4:${Versions.AndroidX.Compose.jUnit}"
            const val testManifest =
                "androidx.compose.ui:ui-test-manifest:${Versions.AndroidX.Compose.jUnit}"
        }

        object Lifecycle {
            const val runtime =
                "androidx.lifecycle:lifecycle-runtime:${Versions.AndroidX.Lifecycle.runtime}"
            const val runtimeKtx =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.Lifecycle.runtimeKtx}"
            const val commonJava8 =
                "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.Lifecycle.commonJava8}"
            const val liveData =
                "androidx.lifecycle:lifecycle-livedata:${Versions.AndroidX.Lifecycle.liveData}"
            const val liveDataKtx =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.Lifecycle.liveDataKtx}"
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel:${Versions.AndroidX.Lifecycle.viewModel}"
            const val viewModelKtx =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.Lifecycle.viewModelKtx}"
        }

        object Room {
            const val runtime = "androidx.room:room-runtime:${Versions.AndroidX.Room.runtime}"
            const val compiler = "androidx.room:room-compiler:${Versions.AndroidX.Room.compiler}"
            const val ktx = "androidx.room:room-ktx:${Versions.AndroidX.Room.ktx}"
            val all = listOf(runtime, compiler, ktx)
        }

        object Tests {
            const val runner =
                "androidx.test:runner:${Versions.AndroidX.Tests.runner}"
            const val rules =
                "androidx.test:rules:${Versions.AndroidX.Tests.rules}"
            const val core =
                "androidx.test:core:${Versions.AndroidX.Tests.core}"
            const val jUnit =
                "junit:junit:${Versions.AndroidX.Tests.jUnit}"
            const val extjUnit =
                "androidx.test.ext:junit:${Versions.AndroidX.Tests.extjUnit}"
            const val archCoreTesting =
                "androidx.arch.core:core-testing:${Versions.AndroidX.Tests.archCoreTesting}"
        }
    }

    object ThirdParty {
        const val coil = "io.coil-kt:coil-compose:${Versions.ThirdParty.coil}"

        object Koin {
            const val core =
                "io.insert-koin:koin-core:${Versions.ThirdParty.Koin.koin}"
            const val android =
                "io.insert-koin:koin-android:${Versions.ThirdParty.Koin.koin}"
            const val compose =
                "io.insert-koin:koin-androidx-compose:${Versions.ThirdParty.Koin.koin}"
            const val jUnit =
                "io.insert-koin:koin-test-junit4:${Versions.ThirdParty.Koin.koin}"
        }

        object Retrofit {
            const val retrofit =
                "com.squareup.retrofit2:retrofit:${Versions.ThirdParty.Retrofit.version}"
            const val moshiConverter =
                "com.squareup.retrofit2:converter-moshi:${Versions.ThirdParty.Retrofit.moshiConverter}"
            const val gsonConverter =
                "com.squareup.retrofit2:converter-gson:${Versions.ThirdParty.Retrofit.gsonConverter}"
            const val scalarsConverter =
                "com.squareup.retrofit2:converter-scalars:${Versions.ThirdParty.Retrofit.scalarsConverter}"
        }

        object Okhttp {
            const val okhttp =
                "com.squareup.okhttp3:okhttp:${Versions.ThirdParty.Okhttp.version}"
            const val loggingInterceptor =
                "com.squareup.okhttp3:logging-interceptor:${Versions.ThirdParty.Okhttp.loggingInterceptor}"
        }

        object UiTest {
            const val roboletric =
                "org.robolectric:robolectric:${Versions.ThirdParty.UiTest.roboletric}"
        }

        object Mockk {
            const val mockk = "io.mockk:mockk:${Versions.ThirdParty.Mockk.mockk}"
            const val mockkAndroid = "io.mockk:mockk-android:${Versions.ThirdParty.Mockk.mockk}"
            const val mockkWebService =
                "com.squareup.okhttp3:mockwebserver:${Versions.ThirdParty.Mockk.mockkWebService}"
            const val mockkAgentJvm = "io.mockk:mockk-agent-jvm:${Versions.ThirdParty.Mockk.mockk}"
        }
    }
}
