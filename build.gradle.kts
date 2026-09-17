plugins {
    java
    alias(libs.plugins.spring.boot)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.starter.web)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.spring.boot.starter.validation)
}

dependencies {
    runtimeOnly(libs.postgresql.driver)
    testImplementation(platform(libs.testcontainers.bom))
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.junit.jupiter)
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
