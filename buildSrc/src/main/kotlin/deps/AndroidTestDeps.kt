package deps

import Versions

object AndroidTestDeps {
    val junit = arrayListOf(
        "androidx.test.ext:junit:${Versions.androidTestJUnit}"
    )

    val runner = arrayListOf(
        "androidx.test:runner:${Versions.runner}"
    )

    val espresso = arrayListOf(
        "androidx.test.espresso:espresso-core:${Versions.espresso}",
        "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    )
}