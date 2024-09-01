plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

repositories {
    mavenCentral()
}

val kitVersion: String by project
val coroutinesVersion: String by project

dependencies {
    implementation("uk.matvey:kit:$kitVersion")

    implementation(project(":"))
}