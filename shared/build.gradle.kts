import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    android()
    //Revert to just ios() when gradle plugin can properly resolve it
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos")?:false
    if(onPhone){
        iosArm64("ios")
    }else{
        iosX64("ios")
    }
    targets.getByName<KotlinNativeTarget>("ios").compilations["main"].kotlinOptions.freeCompilerArgs +=
        listOf("-Xobjc-generics", "-Xg0")

    version = "1.1"

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        val kotlinVersion: String by project
        val commonFirebaseVersion: String by project
        val ktorVersion: String by project

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common", kotlinVersion))
                // Common Deps

                implementation("com.splendo.kaluga:logging:0.0.5")
                implementation("dev.gitlive:firebase-firestore:$commonFirebaseVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

            }
        }

        val androidMain by getting {
            dependsOn(commonMain)

            dependencies {
                implementation(kotlin("stdlib", kotlinVersion))
                // Android Deps
                implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")

            }
        }

        val iosMain by getting {
            dependsOn(commonMain)

            dependencies {
                // iOS Deps
                implementation("io.ktor:ktor-client-serialization-native:$ktorVersion")
            }
        }

    }

    cocoapodsext {
        summary = "FirebaseMPValidation"
        homepage = "homepage"
        framework {
            isStatic = true
        }
    }
}
