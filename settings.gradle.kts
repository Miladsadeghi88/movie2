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
//        maven {
//            url = uri("http://192.168.50.33:8081/repository/maven-public/")
//            isAllowInsecureProtocol = true
//        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
   google()
   mavenCentral()
//        maven {
//            url = uri("http://192.168.50.33:8081/repository/maven-public/")
//            isAllowInsecureProtocol = true
//        }
    }

}
rootProject.name = "movieBox"
include(":app")
 