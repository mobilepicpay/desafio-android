package deps

import Versions

object Deps {
    val kotlin = arrayListOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    )

    val android = arrayListOf(
        "androidx.core:core-ktx:${Versions.androidCore}",
        "androidx.appcompat:appcompat:${Versions.androidAppCompat}",
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}",
        "com.google.android.material:material:${Versions.materialDesign}"
    )

    val lifecycle = arrayListOf(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycle}"
    )

    val circleImageView = arrayListOf(
        "de.hdodenhof:circleimageview:${Versions.circleImageView}"
    )

    val koin = arrayListOf(
        "org.koin:koin-core:${Versions.koin}",
        "org.koin:koin-android:${Versions.koin}",
        "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    )

    val retrofit = arrayListOf(
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}",
        "com.google.code.gson:gson:${Versions.gson}"
    )

    val glide = arrayListOf(
        "com.github.bumptech.glide:glide:${Versions.glide}"
    )

    val timber = arrayListOf(
        "com.jakewharton.timber:timber:${Versions.timber}"
    )
}