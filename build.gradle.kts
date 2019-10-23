import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version("2.1.5.RELEASE")
    id("io.spring.dependency-management") version("1.0.7.RELEASE")
    kotlin("jvm") version("1.3.50")
    kotlin("plugin.spring") version("1.3.50")
}

group = "pl.put.poznan.oculus"
version = "0.0.1"

repositories {
    mavenCentral()
}

val swaggerVersion = "2.9.2"

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // kotlin
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib-jdk8"))

    // swagger / springfox
    implementation("io.springfox:springfox-swagger2:$swaggerVersion")
    implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")

    //test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
