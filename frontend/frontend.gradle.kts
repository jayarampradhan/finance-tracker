import com.moowork.gradle.node.npm.NpmTask
import org.apache.tools.ant.util.Watchdog
import org.gradle.internal.impldep.com.google.api.services.storage.Storage
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

apply {
    plugin("kotlin2js")
    plugin("kotlin-dce-js")
}

plugins {
    id("kotlin2js")
    id("com.moowork.node") version "1.2.0"
    id("kotlin-platform-js")
    id("kotlin-dce-js")
}

node {
    download = true
}
//sourceSets["main"].java {
//    srcDir("src/main/Kotlin")
//}


dependencies {
    compile(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.6.12")
}
tasks.withType<Kotlin2JsCompile> {
    kotlinOptions {
        metaInfo = true
        sourceMap = true
        sourceMapEmbedSources = "always"
        moduleKind = "umd"
        main = "com.farmiculture.financetracker.js.Main"
        target = "v6"
    }
}

task<NpmTask>("buildBundle") {
    dependsOn("npmInstall", "runDceKotlinJs")
    inputs.dir("src/main/kotlin")
    inputs.dir("src/main/web")
    outputs.dir("build/web")
    val command = if (project.hasProperty("prod")) "bundle-prod" else "bundle"
    setArgs(listOf("run", command))

    doLast {
        copy {
            from("build/web")
            into("../ui-app/build/resources/main/static")
        }
        copy {
            from("build/web")
            into("../ui-app/out/production/resources/static")
        }
    }
}

tasks.getByName("build").dependsOn("buildBundle")
