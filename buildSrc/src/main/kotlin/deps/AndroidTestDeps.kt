package deps

import Versions

object AndroidTestDeps {
    val junit = arrayListOf(
        "androidx.test.ext:junit:1.1.2"
    )

    val runner = arrayListOf(
        "androidx.test:runner:${Versions.runner}"
    )

    val espresso = arrayListOf(
        "androidx.test.espresso:espresso-core:${Versions.espresso}"
    )
}