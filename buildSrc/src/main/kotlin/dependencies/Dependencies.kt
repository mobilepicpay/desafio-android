package dependencies

/**
 * To define dependencies
 */
object Dependencies {

    //    View
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy {
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }
    val picasso by lazy { "com.squareup.picasso:picasso:${Versions.picasso}" }
    val circleimageview by lazy { "de.hdodenhof:circleimageview:${Versions.circleimageview}" }

    //    Android Core
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.core}" }
    val core by lazy { "androidx.core:core:${Versions.core}" }

    //    Koin DI
    val koinCore by lazy { "io.insert-koin:koin-core:${Versions.koin}" }
    val koinAndroid by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val testKoin by lazy { "io.insert-koin:koin-test:${Versions.koin}" }

    //    Lifecycle
    val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val livedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}" }
    val runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }

    //   Coroutines
    val coroutinesCore by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }
    val coroutinesAndroid by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }
    val coroutinesTest by lazy {
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    //    Server
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }

    //    Room
    val room by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomTest by lazy { "androidx.room:room-testing:${Versions.room}" }

    //    Test
    val testCore by lazy { "androidx.test:core-ktx:${Versions.testCore}" }
    val testRunner by lazy { "androidx.test:runner:${Versions.testRunner}" }
    val testArch by lazy { "androidx.arch.core:core-testing:${Versions.testArch}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
    val espressoIdling by lazy { "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val androidJunit by lazy { "androidx.test.ext:junit:${Versions.androidJunit}" }

    //    Mock
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val mockWebserver by lazy { "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}" }
}
