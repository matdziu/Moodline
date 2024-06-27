pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Moodline"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":design-system")
include(":diary")
include(":stats")
include(":improve")
include(":add-entry")
include(":domain")
include(":common")
include(":storage")
