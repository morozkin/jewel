import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.archivesName
import org.jmailen.gradle.kotlinter.tasks.LintTask

plugins {
    id("org.jetbrains.jewel.kotlin")
    alias(libs.plugins.composeDesktop)
    id("org.jetbrains.jewel.detekt")
    id("org.jetbrains.jewel.ktlint")
}

dependencies {
    api(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material")
    }
    implementation(libs.jna)
    implementation(libs.kotlinx.serialization.json)
}

tasks.withType<Detekt>().configureEach {
    reports {
        sarif.required.set(true)
        sarif.outputLocation.set(file(rootDir.resolve("build/reports/detekt-${project.archivesName}.sarif")))
    }
}

tasks.withType<LintTask>().configureEach {
    reports.set(mapOf("plain" to rootDir.resolve("build/reports/ktlint-${project.archivesName}.txt")))
    reports.set(mapOf("html" to rootDir.resolve("build/reports/ktlint-${project.archivesName}.html")))
    reports.set(mapOf("sarif" to rootDir.resolve("build/reports/ktlint-${project.archivesName}.sarif")))
}
