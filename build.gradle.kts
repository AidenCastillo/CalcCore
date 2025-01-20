plugins {
    kotlin("jvm") version "1.8.0" // Optional: Use Kotlin DSL for the build script
    `maven-publish`
}

group = "com.github.aidencastillo" // Replace with your GitHub username
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Add any dependencies your library needs
}

tasks.withType<Jar> {
    archiveBaseName.set("calccore")
    archiveVersion.set(version)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

fun <T> Property<T>.set(version: Any) {

}
