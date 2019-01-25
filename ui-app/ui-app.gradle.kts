apply {
    plugin("org.springframework.boot")
    plugin("kotlin-spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    expectedBy(project(":model"))
}


tasks.getByName("processResources").dependsOn(":frontend:build")