import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    kotlin("jvm") version "2.1.20-RC"
    id("com.gradleup.shadow") version "8.3.6"
    `maven-publish`
}

group = "com.linebeck.kotlincore"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
}

tasks.compileJava { options.encoding = "UTF-8" }

tasks.build {
    dependsOn("shadowJar")
}

// Run ShadowJar
tasks.withType<ShadowJar> {
    archiveBaseName.set("KotlinCore")
    archiveClassifier.set("")
    archiveVersion.set(version.toString())
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.shadowJar.get())
        }
    }
}

tasks.named("publishMavenJavaPublicationToMavenLocal") {
    dependsOn("jar", "shadowJar")
}