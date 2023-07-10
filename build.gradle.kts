plugins {
    kotlin("jvm") version "1.7.20"
    id("java")
}

group = "org.isaiahliu"
version = "1.0.0"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDir("src/main/kotlin")
        }
    }
}

tasks {
    processResources {
        from("src/main/kotlin") {
            include("**/*.txt")
        }
    }
}