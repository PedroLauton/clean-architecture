plugins {
    id("java")
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.curso"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // ── Módulos internos do projeto ──────────────────────────
    implementation(project(":core"))
    implementation(project(":usecase"))
    implementation(project(":application"))

    // ── Core Spring ──────────────────────────────────────────
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // ── JWT ──────────────────────────────────────────────────
    implementation("com.auth0:java-jwt:4.5.1")

    // ── Banco de dados ───────────────────────────────────────
    //implementation("org.flywaydb:flyway-core")
    //implementation("org.flywaydb:flyway-mysql")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.h2database:h2")

    // ── Utilitários ──────────────────────────────────────────
    implementation("org.apache.commons:commons-lang3:3.20.0")

    // ── Lombok ───────────────────────────────────────────────
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // ── Testes ───────────────────────────────────────────────
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // testRuntimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}