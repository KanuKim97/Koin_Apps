plugins {
    id(PlugIns.android_library)
    id(PlugIns.kotlin_android)
    id(PlugIns.ksp)
    id(PlugIns.dagger_hilt)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.koin.common"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.androidx_core)

    implementation(Dependencies.hilt)
    ksp(Dependencies.hilt_compiler)

    implementation(Dependencies.coroutine)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
}