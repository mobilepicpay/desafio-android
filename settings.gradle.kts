pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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

include (":app")

include(":core:common")
include (":core:data")
include (":core:database")
include (":core:datastore")
include(":core:design")
include(":core:domain")
include(":core:model")
include (":core:network")
include(":core:testing")

include(":feature:contacts")
include(":feature:dinogame")

include (":sync:work")
include (":sync:sync-test")
