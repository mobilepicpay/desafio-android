//apply("$rootDir/hooks.gradle")
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.hilt.get()}")
    }

}//

plugins {
    val kotlinVersion = libs.versions.kotlin.get()
    val agpVersion = libs.versions.agp.get()
    val gradleUpdate = libs.versions.gradleUpdate.get()
    val detekt = libs.versions.detekt.get()

    id("com.android.application") version (agpVersion) apply (false)
    id("com.android.library") version (agpVersion) apply (false)
    id("org.jetbrains.kotlin.android") version (kotlinVersion) apply (false)
    id("org.jetbrains.kotlin.jvm") version (kotlinVersion) apply (false)
    id("com.github.ben-manes.versions") version (gradleUpdate)
    id("io.gitlab.arturbosch.detekt") version (detekt)
}

/**
 * Apparently there is a bug with objenesis 3.1 (which is a transitive dependency of mockk)
 * that causes a crash, forcing objenesis downgrade to 2.6 fixes this issue
 * @see [Issue link](https://github.com/mockk/mockk/issues/281)
 * */
allprojects {
    configurations.all {
        resolutionStrategy {
            force("org.objenesis:objenesis:2.6")
        }
    }
}
