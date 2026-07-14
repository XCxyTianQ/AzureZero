pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The AzureZero project directory is not a properly cloned Git repository.
         
         In order to build AzureZero from source you must clone
         the AzureZero repository using Git, not download a code
         zip from GitHub.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "azurezero"

for ((projectName, dirName) in listOf("azurezero-api" to "paper-api", "azurezero-server" to "paper-server")) {
    include(projectName)
    project(":$projectName").projectDir = file(dirName)
}

optionalInclude("test-plugin")
optionalInclude("azurezero-generator")

fun optionalInclude(name: String, op: (ProjectDescriptor.() -> Unit)? = null) {
    val settingsFile = file("$name.settings.gradle.kts")
    if (settingsFile.exists()) {
        apply(from = settingsFile)
        findProject(":$name")?.let { op?.invoke(it) }
    } else {
        settingsFile.writeText(
            """
            // Uncomment to enable the '$name' project
            // include(":$name")

            """.trimIndent()
        )
    }
}

gradle.lifecycle.beforeProject {
    val mcVersion = providers.gradleProperty("mcVersion").get().trim()
    val channel = providers.gradleProperty("channel").get().trim()
    val buildNumber = providers.environmentVariable("BUILD_NUMBER").orNull?.trim()?.toInt()
    val versionString = if (buildNumber == null) {
        "$mcVersion.local-SNAPSHOT"
    } else {
        "$mcVersion.build.$buildNumber-${channel.lowercase()}"
    }
    version = versionString
}
