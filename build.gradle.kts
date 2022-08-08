plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // 协程Java8支持库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    // lifecycle对于协程的扩展封装
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}


//tasks.test {
//    useJUnitPlatform()
//}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("KotlinTestKt")
}