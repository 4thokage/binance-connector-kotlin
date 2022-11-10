import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    `maven-publish`
}

group = "pt.zenit.helpers"
version = "1.0-SNAPSHOT"
var versionAux = version

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    implementation("com.beust:klaxon:5.6")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.2")
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "$group"
            artifactId = rootProject.name
            version = "$versionAux"

            from(components["java"])
        }
    }
}

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
