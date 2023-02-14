plugins {
    kotlin("jvm") version "1.7.20"
}

group = "org.isaiahliu"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks {
    processResources {
        from("src/main/kotlin") {
            include("**/*.txt")
        }
    }
}