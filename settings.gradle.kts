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
rootProject.name = "MxVideoPlugin"
include(":MxVideoPlugin")

val basePluginApi = ":BaseVideoPluginFramework"
include(basePluginApi)
project(basePluginApi).projectDir = File("./submodules/MediaBoxBasePlugin/BaseVideoPluginFramework")

val pluginApi = ":MediaBoxPluginApi"
include(pluginApi)
project(pluginApi).projectDir = File("./submodules/MediaBoxPlugin/pluginApi")