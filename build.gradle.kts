import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.1.4"

    // kotlin
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("kapt") version "1.6.10"

    id("org.openapi.generator") version "6.6.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

// springboot 2.6.x 에서 kotest 를 사용하기 위한 설정
extra["kotlin-coroutines.version"] = "1.6.0"

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation ("org.springframework.boot:spring-boot-starter-jdbc")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // swagger (springfox 는 개발 지원 중단. springdoc 으로 대체)
    implementation("org.springdoc:springdoc-openapi-ui:1.6.8")
    implementation("org.springdoc:springdoc-openapi-common:1.6.8")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.8")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.0")

    // swagger 3.0  ->
//    implementation("io.springfox:springfox-boot-starter:3.0.0")
//    implementation("io.springfox:springfox-swagger-ui:3.0.0")
//    implementation("io.swagger.core.v3:swagger-annotations-jakarta:2.2.11")

    // openapi generator
    implementation("io.swagger:swagger-annotations:1.6.2")
    implementation(group = "javax.validation", name = "validation-api", version = "2.0.1.Final")
    implementation(group = "org.openapitools", name = "jackson-databind-nullable", version = "0.2.1")

//    implementation("org.openapitools:openapi-generator:6.0.0") {
//        exclude(group = "org.slf4j", module = "slf4j-simple")
//    }

//    implementation("org.openapitools:openapi-generator-gradle-plugin:5.1.1") {
//        exclude(group = "org.slf4j", module = "slf4j-simple")
//    }

    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.3") // kotlin junit 처럼 쓸 수 있는 Spec 들이 정의 됨

    testImplementation("io.kotest:kotest-assertions-core:4.4.3") // shouldBe... etc 와같이 Assertions 의 기능을 제공
    testImplementation("io.kotest:kotest-framework-datatest:4.5.0") // data driven test 를 위한 lib
    testImplementation("io.kotest:kotest-extensions-spring:4.4.3") // spring boot test 를 위해서 추가
    testImplementation("com.ninja-squad:springmockk:3.1.1") // junit 의 @MockBean @SpyBean 과 같은 기능을 제공 해주는 lib
    testImplementation("io.mockk:mockk:1.12.8") // unit test 에서 mockking 사용
}

// kotlin 컴파일러
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

configure<SourceSetContainer> {
    named("main") {
        java.srcDir("$buildDir/generated-sources/src/main/kotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<GenerateTask>("generateFromYaml"){
    verbose.set(true)
    generatorName.set("kotlin-spring")
    library.set("spring-boot")
    inputSpec.set("$projectDir/src/main/resources/specs/petstore.yaml")
    outputDir.set("$buildDir/generated-sources")
    apiPackage.set("com.example.treedbtest.api")
    invokerPackage.set("com.example.treedbtest")
    modelPackage.set("com.example.treedbtest.model")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "hideGenerationTimestamp" to "true",
            "delegatePattern" to "true",
            "useTags" to "true"
        )
    )

    group = "1.action"
}

tasks.named("compileKotlin").configure {
    dependsOn("generateFromYaml")
}


//openApiGenerate {
//    println("openapiGenerate start!")
//    verbose.set(true)
//    generatorName.set("kotlin-spring")
//    library.set("spring-boot")
//    inputSpec.set("$rootDir/src/main/resources/specs/petstore.yaml")
//    outputDir.set("$buildDir/generated-sources")
//    apiPackage.set("com.example.treedbtest.api")
//    invokerPackage.set("com.example.treedbtest.invoker")
//    modelPackage.set("com.example.treedbtest.model")
//    configOptions.set(
//        mapOf(
//            "dateLibrary" to "java8"
//        )
//    )
//}

//tasks.bootBuildImage {
//    builder.set("paketobuildpacks/builder-jammy-base:latest")
//}
