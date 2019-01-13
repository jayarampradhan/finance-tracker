import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
	val kotlinVersion by System.getProperties()
	val springVersion by System.getProperties()
    repositories {
        mavenCentral()
	}

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("io.spring.gradle:dependency-management-plugin:1.0.6.RELEASE")
//        classpath("com.moowork.node:gradle-node-plugin:1.2.0")
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
	base
	id("org.jetbrains.kotlin.jvm") version "$kotlinVersion" apply true
    id("io.spring.dependency-management") version "1.0.6.RELEASE" apply true
    id("org.springframework.boot") version "$springVersion" apply false
    id ("org.jetbrains.dokka") version "0.9.17" apply true
    id ("org.jetbrains.kotlin.plugin.jpa") version "$kotlinVersion" apply false
}



subprojects {
	val springVersion by System.getProperties()
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    java.sourceCompatibility = JavaVersion.VERSION_1_8
    java.targetCompatibility = JavaVersion.VERSION_1_8

    repositories {
        mavenCentral()
    }

    dependencyManagement {
        imports { mavenBom("org.springframework.boot:spring-boot-dependencies:$springVersion") }
    }
}
dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}

