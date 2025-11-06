pluginManagement {
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

rootProject.name = "Demo_Android"
include(":core")
include(":app")
include(":compose_navigation")
include(":compose_custom_bottom_sheet_scaffold")
include(":dagger2")
include(":compose_detect_windowinsets")
include(":preferences_datastore")
include(":retrofit2")
include(":room")
include(":gemini")
