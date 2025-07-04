plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "6.6.0"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val postgresVersion = "42.7.2"
val testContainersVersion = "1.20.1"
val openApiVersion = "2.6.0"
val mapstructVersion = "1.5.0.Final"
val liquibaseVersion = "4.27.0"

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openApiVersion")

    // Database
    implementation("org.liquibase:liquibase-core:$liquibaseVersion")
    runtimeOnly("org.postgresql:postgresql:$postgresVersion")

    // Mapping
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")


    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter:$testContainersVersion")
    testImplementation("org.testcontainers:postgresql:$testContainersVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    openApiGenerate {
        generatorName.set("kotlin-spring")
        outputDir.set("$buildDir/generated")
        configOptions.put("useSpringBoot3", "true")
        configOptions.put("useTags", "true")
        configOptions.put("useOptional", "true")
        configOptions.put("dateLibrary", "java8")
        configOptions.put("interfaceOnly", "true")

        inputSpec.set("$projectDir/src/main/resources/swagger.yaml")
        packageName.set("org.example.hospital.generated")
        apiPackage.set("org.example.hospital.generated.api")
        modelPackage.set("org.example.hospital.generated.api.model")
        invokerPackage.set("org.example.hospital.generated.invoker")
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        dependsOn(openApiGenerate)
    }
}


sourceSets {
    main {
        java {
            srcDir("$buildDir/generated")
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2024.0.1")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}