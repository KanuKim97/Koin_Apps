plugins {
    id("java-library")
    id(PlugIns.kotlin_jvm)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}