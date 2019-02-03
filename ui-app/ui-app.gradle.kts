apply {
    plugin("org.springframework.boot")
    plugin("kotlin-spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    implementation("org.tuckey:urlrewritefilter:4.0.3")
//	compileOnly("javax.servlet:javax.servlet-api:4.0.1")
//	implementation("javax.servlet:javax.servlet-api:4.0.1")
    expectedBy(project(":model"))
}


tasks.getByName("processResources").dependsOn(":frontend:build")