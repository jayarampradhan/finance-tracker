import com.moowork.gradle.node.npm.NpmInstallTask
import com.moowork.gradle.node.npm.NpmTask
import org.apache.tools.ant.util.Watchdog
import org.gradle.internal.impldep.com.google.api.services.storage.Storage
import org.jetbrains.kotlin.cli.common.arguments.K2JSDceArguments
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJsDce

apply {
    plugin("kotlin2js")
    plugin("kotlin-dce-js")
}

plugins {
    id("com.moowork.node") version "1.2.0"
    id("kotlin-platform-js")
}

node {
    download = true
}
//sourceSets["main"].java {
//    srcDir("src/main/Kotlin")
//}


dependencies {
	val kotlinxVersion by System.getProperties()
    compile(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxVersion")
	expectedBy(project(":model"))
}
tasks.withType<Kotlin2JsCompile> {
    kotlinOptions {
        metaInfo = true
        sourceMap = true
        sourceMapEmbedSources = "never"
        moduleKind = "umd"
        target = "v6"
    }
}
tasks.withType<KotlinJsDce> {
    val args = K2JSDceArguments()
    args.printReachabilityInfo = true
    setupCompilerArgs(args)
}

/**
 * even though the `runDceKotlinJs` is called,
 * actual DCE is not happening because of a bug in kotlin version 1.3.20
 * actual build is happening from the npm modules,
 * when fixed remove the kotlin dep from npm package.json and make the resolve details as well.
 */
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
