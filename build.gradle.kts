import org.gradle.internal.impldep.org.bouncycastle.asn1.crmf.SinglePubInfo.web

plugins {
	java
	id("org.springframework.boot") version "3.2.10"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.arbitrage"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.3"
extra["postgresqlVersion"] = "42.5.0"
extra["lombokVersion"] = "1.18.24"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.postgresql:postgresql:${property("postgresqlVersion")}")
	compileOnly("org.projectlombok:lombok:${property("lombokVersion")}")
	annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
