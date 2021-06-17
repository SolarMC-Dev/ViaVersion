import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.named

plugins {
    id("via.base-conventions")
    id("com.github.johnrengelman.shadow")
}

// Solar start
val jar = tasks.named<Jar>("jar") {
    archiveClassifier.set("unshaded")
    from(project.rootProject.file("LICENSE"))
}
val shadowJar = tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    configureRelocations()
}
tasks {
    named("build") {
        dependsOn(shadowJar)
    }
}
artifacts {
    archives(shadowJar.get().archiveFile) {
        classifier = ""
        builtBy(shadowJar)
    }
    archives(jar.get().archiveFile) {
        classifier = "unshaded"
        builtBy(jar)
    }
}
// Gradle module metadata points to the unshaded jar, whereas Maven uses the default classifier
// Disble it to prevent issues
tasks.withType<GenerateModuleMetadata> {
    enabled = false
}
// Solar end

publishShadowJar()

// Solar start - shade only opennbt
fun ShadowJar.configureRelocations() {
    relocate("com.github.steveice10.opennbt", "com.viaversion.viaversion.libs.opennbt")
}
// Solar end
