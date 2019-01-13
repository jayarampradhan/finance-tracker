import org.gradle.internal.impldep.org.apache.maven.repository.internal.VersionsMetadataGeneratorFactory
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.util.visibleName
import org.springframework.boot.gradle.tasks.bundling.BootJar


apply {
    plugin("org.springframework.boot")
    plugin("kotlin-platform-jvm")
    plugin("kotlin-spring")
}

dependencies {
    "compile"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    "compile"("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    "expectedBy"(project(":common"))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

}

tasks.getByName("processResources").dependsOn(":frontend:build")