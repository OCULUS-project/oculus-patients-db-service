import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
}

group = "pl.put.poznan.oculus"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/jakubriegel/oculus")
}

dependencies {
    // oculus
    implementation("pl.poznan.put.oculus.boot:oculus-spring-boot-starter:0.2.2")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    //test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.3"
}
