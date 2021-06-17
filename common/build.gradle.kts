import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar // Solar

plugins {
    id("net.kyori.blossom")
    id("via.shadow-conventions") // Solar
}

blossom {
    replaceToken("\$VERSION", project.version)
    replaceToken("\$IMPL_VERSION", "git-ViaVersion-${project.version}:${rootProject.latestCommitHash()}")
}

dependencies {
    api(projects.viaversionApi)
    api(projects.viaversionApiLegacy)
    compileOnly(libs.caffeine) // Solar
}

// Solar start
tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("nothing:nothing:.*"))
    }
}
// Solar end

java {
    withJavadocJar()
}
