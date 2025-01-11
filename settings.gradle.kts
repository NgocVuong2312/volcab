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

rootProject.name = "clock"
include(":app")
include(":app:clock")
include(":app:unitconverter")
include(":app:calculator")
include(":app:timemanager")
include(":app:timemanager2")
include(":app:navigationbar")
include(":app:navigationbar2")
include(":app:navigationbar22")
include(":app:myapplication")
include(":app:expensify")
include(":app:note")
include(":app:quiz")
include(":app:volcab")
