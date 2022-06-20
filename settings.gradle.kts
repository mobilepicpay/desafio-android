pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "desafio-android"
include(
    ":ui",
    ":app",
    ":network",
    ":feature:contactlist:presentation",
    ":feature:contactlist:datasource:remote",
    ":feature:contactlist:datasource:internal",
    ":feature:contactlist:repository:impl",
    ":feature:contactlist:repository:public",
    ":feature:contactlist:usecase:impl",
    ":feature:contactlist:usecase:public",
    ":testlib"
)
