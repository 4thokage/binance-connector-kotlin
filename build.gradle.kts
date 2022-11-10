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
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    implementation("com.beust:klaxon:5.6")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.20")
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

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/4thokage/binance-connector-kotlin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

// auto accept gradle build scan
if (hasProperty("buildScan")) {
    extensions.findByName("buildScan")?.withGroovyBuilder {
        setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
        setProperty("termsOfServiceAgree", "yes")
    }
}

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
