plugins {
    id(PlugIns.android_library)
    id(PlugIns.kotlin_android)
    id(PlugIns.ksp)
    id(PlugIns.dagger_hilt)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.koin.network"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(Dependencies.androidx_core)

    implementation(project(Module.core_model))

    implementation(Dependencies.hilt)
    ksp(Dependencies.hilt_compiler)

    implementation(Dependencies.retrofit2)
    implementation(Dependencies.retrofit2_convertor_gson)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
}