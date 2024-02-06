buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id(PlugIns.android_application) version Versions.gradle apply false
    id(PlugIns.android_library) version Versions.gradle apply false
    id(PlugIns.kotlin_android) version Versions.kotlin apply false
    id(PlugIns.kotlin_jvm) version Versions.kotlin apply false
    id(PlugIns.ksp) version Versions.ksp apply false
    id(PlugIns.dagger_hilt) version Versions.dagger_hilt apply false
}