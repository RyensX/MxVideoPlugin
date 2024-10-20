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

val baseFrameworkPath = "./submodules/MediaBoxBasePlugin"

val basePluginApi = ":BaseVideoPluginFramework"
include(basePluginApi)
project(basePluginApi).projectDir = File("$baseFrameworkPath/BaseVideoPluginFramework")

val pluginApi = ":MediaBoxPluginApi"
include(pluginApi)
project(pluginApi).projectDir = File("$baseFrameworkPath/submodules/MediaBoxPlugin/pluginApi")
