//buildscript {
//	repositories {
//		mavenCentral()
//	}
//
//	dependencies {
//		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.21")
//	}
//}
plugins {
//	kotlin("multiplatform")
//	id("kotlinx-serialization")
}
apply{
//    plugin("kotlin-platform-common")
//	plugin("kotlin-multiplatform")
	plugin("kotlin-platform-common")
//	plugin("kotlinx-serialization")
//	plugin("java")
}
dependencies {
//    expectedBy(":frontend")
	"compile"("org.jetbrains.kotlin:kotlin-stdlib-common")
	"compile"("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
//	"compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.10.0")
//	"compile"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

}

//kotlin {
//	jvm() {
//		compilations["main"].defaultSourceSet {
//			dependencies {
//				implementation(kotlin("stdlib-jdk8"))
//			}
//		}
//		compilations["test"].defaultSourceSet {
//			dependencies {
//				implementation(kotlin("test-junit"))
//			}
//		}
//		val main by compilations.getting {
//			kotlinOptions {
//				// Setup the Kotlin compiler options for the 'main' compilation:
//				jvmTarget = "1.8"
//			}
//
//			compileKotlinTask // get the Kotlin task 'compileKotlinJvm'
//			output // get the main compilation output
//		}
//
//		compilations["test"].runtimeDependencyFiles // get the test runtime classpath
//
//	}
//	js()
//}
