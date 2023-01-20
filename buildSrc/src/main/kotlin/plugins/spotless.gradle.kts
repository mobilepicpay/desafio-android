package plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

@Suppress("INACCESSIBLE_TYPE")
configure<SpotlessExtension> {

    format("xml") {
        target("**/res/**/*.xml")
        targetExclude("**/build/**")
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target ("**/*.kt")
        ktfmt()
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlinGradle {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                    "exclude" to listOf("**/build/**")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
