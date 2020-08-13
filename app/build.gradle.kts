plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    defaultConfig {
        applicationId = "com.corrado4eyes.zoomdemoandroid"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    val kotlinVersion: String by project
    val coreKtxVersion: String by project
    val appCompatVersion: String by project
    val constraintLayoutVersion: String by project
    val coroutinesVersion: String by project

    val junitVersion: String by project
    val extJunitVersion: String by project
    val espressoCoreVersion: String by project

    implementation(project(":shared"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", kotlinVersion))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation(
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    )
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$extJunitVersion")
    androidTestImplementation (
            "androidx.test.espresso:espresso-core:$espressoCoreVersion"
    )

}