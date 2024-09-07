plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.application)
        alias(kotlinKapt)
    }
    id("kotlin-parcelize")
}

android {
    with(libs.versions) {
        namespace = appId.get()
        compileSdk = androidApiCompile.get().toInt()

        defaultConfig {
            applicationId = appId.get()
            minSdk =  androidApiMin.get().toInt()
            //noinspection ExpiredTargetSdkVersion
            targetSdk =  androidApiTarget.get().toInt()
            versionCode = appVersionCode.get().toInt()
            versionName = appVersionName.get()

            testInstrumentationRunner = libs.versions.testInstrumentationRunner.get()
            vectorDrawables {
                useSupportLibrary = true
            }
        }

        signingConfigs {
            getByName("debug") {
                storeFile = File(rootProject.projectDir.toString() + "/app/key.jks")
                storePassword = "password"
                keyAlias = "key0"
                keyPassword = "password"
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                signingConfig = signingConfigs.getByName("debug")
            }
        }
        compileOptions {
            sourceCompatibility(libs.versions.java.get())
            targetCompatibility(libs.versions.java.get())
        }

        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = kotinExtensionVersion.get()
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                merges += "META-INF/LICENSE.md"
                merges += "META-INF/LICENSE-notice.md"
            }
        }
        kotlinOptions {
            jvmTarget = java.get()
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:design"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:testing"))

    implementation(project(":feature:contacts"))
    implementation(project(":feature:dinogame"))

    implementation(project(":sync:work"))
    implementation(project(":sync:sync-test"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
