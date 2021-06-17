import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar // Solar

dependencies {
    implementation(projects.viaversionCommon)
    compileOnly(libs.velocity)
    annotationProcessor(libs.velocity)
}

// Solar start
tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("com.viaversion:opennbt"))
        include(dependency("gg.solarmc.viaversion:.*"))
    }
}
// Solar end
