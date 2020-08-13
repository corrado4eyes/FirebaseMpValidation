// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion: String by project

    val buildGradleVersion: String by project
    val nativeCocoaPodsVersion: String by project
    val googleServicesVersion: String by project

    repositories {
        mavenLocal()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$buildGradleVersion")
        classpath(kotlin("gradle-plugin", kotlinVersion))
        classpath(kotlin("serialization", kotlinVersion))

        // Pods
        classpath("co.touchlab:kotlinnativecocoapods:$nativeCocoaPodsVersion")

        // Google Services
        classpath("com.google.gms:google-services:$googleServicesVersion")


    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
    }
}

listOf("bootstrap", "update").forEach {
    task<Exec>("carthage${it.capitalize()}") {
        group = "carthage"
        executable = "carthage"
        args(
            it,
            "--project-directory", "shared/src/iosMain/c_interop",
            "--platform", "iOS",
            "--cache-builds"
        )
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}