import org.jetbrains.kotlin.codegen.JvmKotlinType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	val kotlinVersion by System.getProperties()
	val springVersion by System.getProperties()
    repositories {
        mavenCentral()
	}

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("io.spring.gradle:dependency-management-plugin:1.0.6.RELEASE")
		classpath(kotlin("gradle-plugin", version = "$kotlinVersion"))
		classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    }
}
allprojects {
    group = "com.farmiculture.financetracker"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
		jcenter()
    }
    apply(plugin = "idea")
}
plugins {
	val kotlinVersion by System.getProperties()
	val springVersion by System.getProperties()
	id("java") apply true
	kotlin("jvm") version "$kotlinVersion" apply false
    id("io.spring.dependency-management") version "1.0.6.RELEASE" apply true
    id("org.springframework.boot") version "$springVersion" apply false
    id ("org.jetbrains.dokka") version "0.9.17" apply true
    id ("org.jetbrains.kotlin.plugin.jpa") version "$kotlinVersion" apply false
}

configure(subprojects.filter { it.name != "frontend" && it.name != "model"}) {
	val springVersion by System.getProperties()
	val junitVersion by System.getProperties()
	val kotlinVersion by System.getProperties()
	apply(plugin = "kotlin")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "kotlin-platform-jvm")

	java.sourceCompatibility = JavaVersion.VERSION_1_8
	java.targetCompatibility = JavaVersion.VERSION_1_8

	tasks {
		withType<KotlinCompile> {
			kotlinOptions {
				jvmTarget = JavaVersion.VERSION_1_8.toString()
				freeCompilerArgs = listOf("-Xjsr305=strict")
			}
		}
	}
	dependencyManagement {
		imports { mavenBom("org.springframework.boot:spring-boot-dependencies:$springVersion") }
	}
	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		testImplementation("junit:junit:$junitVersion")
	}
}

subprojects {

}
dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}

