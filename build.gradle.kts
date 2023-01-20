buildscript {

    repositories {
        google()
        mavenCentral()
    }
}

plugins.apply("plugins.update-dependencies")

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    plugins.apply("plugins.detekt")
    plugins.apply("plugins.spotless")
}
