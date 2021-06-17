import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar // Solar

plugins {
    id("net.kyori.blossom")
    id("via.shadow-conventions")
}

blossom {
    replaceToken("\$VERSION", project.version)
    replaceToken("\$IMPL_VERSION", "git-ViaVersion-${project.version}:${rootProject.latestCommitHash()}")
}

dependencies {
    api(libs.bundles.adventure) // Solar
    compileOnlyApi(libs.fastutil) // Solar - compileOnlyApi
    api(libs.openNBT)
    compileOnlyApi(libs.gson) // Solar - compileOnlyApi
    compileOnlyApi("org.slf4j:slf4j-api:1.7.30") // Solar - add slf4j

    compileOnlyApi(libs.snakeYaml)
    compileOnlyApi(libs.netty)
    compileOnlyApi(libs.guava)
    compileOnlyApi(libs.checkerQual)
}


// Solar start
tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency(":opennbt:.*"))
    }
}
// Solar end

java {
    withJavadocJar()
}
