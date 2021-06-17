import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar // Solar

dependencies {
    implementation(projects.viaversionBukkitLegacy)
    implementation(projects.viaversionCommon)
// Solar start
    //implementation(libs.javassist)
    compileOnly(libs.bundles.adventure)
    compileOnly(libs.paper)
// Solar end
}

// Solar start
tasks.named<ShadowJar>("shadowJar") {
    dependencies {
        include(dependency("com.viaversion:opennbt"))
        include(dependency("gg.solarmc.viaversion:.*"))
    }
}
// Solar end
