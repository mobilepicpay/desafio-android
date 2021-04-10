package deps

import Versions

object AndroidTestDeps {
    val runner = arrayListOf(
        "androidx.test:runner:${Versions.runner}"
    )

    val espresso = arrayListOf(
        "androidx.test.espresso:espresso-core:${Versions.espresso}"
    )
}