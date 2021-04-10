package deps

import Versions

object TestDeps {

    val junit = arrayListOf(
        "junit:junit:${Versions.junit}"
    )

    val truth = arrayListOf(
        "com.google.truth:truth:${Versions.truth}"
    )

    val mockk = arrayListOf(
        "io.mockk:mockk:${Versions.mockk}"
    )

    val coroutines = arrayListOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutinesTest}"
    )
}