plugins {
    kotlin("jvm") version "2.2.0"
    id("java")
}

group = "org.isaiahliu"
version = "1.0.0"

val REPOSITORY_MIRRORS = arrayOf(
    "https://maven.aliyun.com/repository/central",
    "https://maven.aliyun.com/repository/jcenter",
    "https://maven.aliyun.com/repository/google",
    "https://maven.aliyun.com/repository/gradle-plugin"
)

repositories {
    mavenLocal()

    REPOSITORY_MIRRORS.forEach {
        maven { url = uri(it) }
    }

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

