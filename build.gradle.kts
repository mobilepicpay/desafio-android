// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    with(libs.plugins) {
        alias(android.application) apply false
        alias(android.library) apply false

        alias(jetbrains.kotlin.android) apply false

        alias(kotlinJvm) apply false
        alias(kotlinKapt) apply false
    }
}

subprojects {
    tasks.withType<Test> {
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = true
        }
        reports {
            junitXml.isOutputPerTestCase = true
        }
    }
}