enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    // configures repositories for all projects
    repositories {
        maven("https://repo.viaversion.com")
// Solar start
        //maven("https://oss.sonatype.org/content/repositories/snapshots/")
        //maven("https://nexus.velocitypowered.com/repository/maven-public/")
        //maven("https://repo.spongepowered.org/repository/maven-public/")
        //maven("https://libraries.minecraft.net")
        mavenCentral()
        maven("https://repo.solarmc.gg/mvn")
// Solar end
    }
    // only use these repos
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

pluginManagement {
    // default plugin versions
    plugins {
        id("net.kyori.blossom") version "1.2.0"
        id("com.github.johnrengelman.shadow") version "7.1.0"
    }
}

rootProject.name = "viaversion-parent"

includeBuild("build-logic")

/* Solar start
include("adventure")
include("java-compat", "java-compat:java-compat-common", "java-compat:java-compat-unsafe")
*/ // Solar end

setupViaSubproject("api")
setupViaSubproject("api-legacy")
setupViaSubproject("common")
setupViaSubproject("bukkit")
setupViaSubproject("bukkit-legacy")
// Solar start
setupViaSubproject("velocity")
/*
setupViaSubproject("bungee")
setupViaSubproject("velocity")
setupViaSubproject("sponge")
setupViaSubproject("sponge-legacy")
setupViaSubproject("fabric")
*/ // Solar end

setupSubproject("viaversion") {
    projectDir = file("universal")
}

fun setupViaSubproject(name: String) {
    setupSubproject("viaversion-$name") {
        projectDir = file(name)
    }
}

inline fun setupSubproject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}
