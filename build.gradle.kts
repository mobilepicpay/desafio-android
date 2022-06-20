import com.picpay.desafio.android.config.AppConfig
import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version com.picpay.desafio.android.Versions.Android.gradle apply false
    id("com.android.library") version com.picpay.desafio.android.Versions.Android.gradle apply false
    id("org.jetbrains.kotlin.android") version com.picpay.desafio.android.Versions.JetBrains.Kotlin.gradle apply false
}

allprojects {

    configurations.all {
        resolutionStrategy {
            force("org.intellij:annotations:13.0")
            exclude(group = "com.intellij", module = "annotations")
        }
    }

    afterEvaluate {
        val androidExtensions = this.extensions.findByName("android")
        (androidExtensions as? BaseExtension)?.apply {
            compileSdkVersion(AppConfig.compileSdk)

            defaultConfig {
                minSdk = AppConfig.minSdk
                targetSdk = AppConfig.targetSdk

                testInstrumentationRunner =
                    AppConfig.androidTestInstrumentation

            }

            compileOptions {
                sourceCompatibility =
                    AppConfig.CompileOptions.javaSourceCompatibility
                targetCompatibility =
                    AppConfig.CompileOptions.javaSourceCompatibility
            }

            tasks.withType<KotlinCompile> {
                kotlinOptions {
                    jvmTarget = AppConfig.CompileOptions.kotlinJvmTarget
                }
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}