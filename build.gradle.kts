plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(libs.assertj.core)
}

java {
    withJavadocJar()
    withSourcesJar()
}

kotlin {
    jvmToolchain(11)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "uk.matvey"
            artifactId = "telek"
            version = project.findProperty("releaseVersion") as? String ?: "0.1.0-SNAPSHOT"

            from(components["java"])

            pom {
                name = "Telek"
                description = "Kotlin Telegram Bot API client"
                url = "https://github.com/msmych/telek"

                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "https://spdx.org/licenses/Apache-2.0.html"
                    }
                }
                developers {
                    developer {
                        id = "msmych"
                        name = "Matvey Smychkov"
                        email = "realsmych@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/msmych/telek.git"
                    developerConnection = "scm:git:ssh://github.com/msmych/telek.git"
                    url = "https://github.com/msmych/telek"
                }
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/msmych/telek")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GH_PACKAGES_RW_TOKEN")
                }
            }
        }
    }
}
