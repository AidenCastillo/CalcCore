plugins {
    kotlin("jvm") version "1.8.0" // Optional: Use Kotlin DSL for the build script
    `maven-publish`
}

group = "com.github.aidencastillo.calccore" // Replace with your GitHub username
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Add any dependencies your library needs
    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
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

sourceSets {
    test {
        java {
            srcDir("src/test.java")
        }
    }
}

fun <T> Property<T>.set(version: Any) {

}
