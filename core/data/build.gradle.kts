plugins {
    id(PlugIns.android_library)
    id(PlugIns.kotlin_android)
    id(PlugIns.ksp)
    id(PlugIns.dagger_hilt)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.koin.data"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.androidx_core)

    implementation(project(Module.core_common))
    implementation(project(Module.core_network))
    implementation(project(Module.core_database))
    implementation(project(Module.core_model))

    implementation(Dependencies.hilt)
    ksp(Dependencies.hilt_compiler)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
}