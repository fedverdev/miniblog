val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.2.0"
    id("com.google.protobuf") version "0.9.5"

}

group = "com.github.fedverdev"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    /* gRPC */
    implementation("io.grpc:grpc-netty-shaded:1.73.0")
    implementation("io.grpc:grpc-protobuf:1.73.0")
    implementation("io.grpc:grpc-stub:1.73.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    /* db */
    implementation("org.jetbrains.exposed:exposed-core:0.61.0")
    runtimeOnly("org.jetbrains.exposed:exposed-jdbc:0.61.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.61.0")
    implementation("com.zaxxer:HikariCP:6.3.0")
    implementation("org.flywaydb:flyway-database-postgresql:11.9.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.61.0")

    implementation("org.postgresql:postgresql:42.7.7")

    /* config */
    implementation("com.typesafe:config:1.4.3")
    implementation("io.ktor:ktor-server-config-yaml:3.2.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.0.0"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.0.0-pre2"
        }

    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
            }
        }
    }
}

tasks.named("shadowJar") {
    (this as com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar).apply {
        archiveFileName.set("profile-service.jar")
        mergeServiceFiles()
        manifest {
            attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
        }
    }
}
