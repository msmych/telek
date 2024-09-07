plugins {
    kotlin("jvm") version "2.0.0"
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/msmych/kit")
        credentials {
            username = "telek"
            password = project.findProperty("ghPackagesRoToken") as? String ?: System.getenv("GH_PACKAGES_RO_TOKEN")
        }
    }
}

val kitVersion: String by project

dependencies {
    implementation("uk.matvey:kit:$kitVersion")

    implementation(project(":"))
}