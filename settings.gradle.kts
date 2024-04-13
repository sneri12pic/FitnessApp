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
        //Charts
        maven{ url = uri("https://jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "FitnessApp"
include(":app")
 