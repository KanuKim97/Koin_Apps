plugins {
    id(PlugIns.android_library)
    id(PlugIns.kotlin_android)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.koin.designsystem"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.compose
}

dependencies {
    implementation(Dependencies.androidx_core)

    val composeBoM = platform(Dependencies.composeBoM)
    implementation(composeBoM)
    implementation(Dependencies.compose_activity)
    implementation(Dependencies.compose_material3)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_ui_graphics)
    implementation(Dependencies.compose_ui_preview)
    implementation(Dependencies.compose_livedata)
    implementation(Dependencies.compose_material_icon)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
    androidTestImplementation(Dependencies.espresso_core)

    androidTestImplementation(composeBoM)
    androidTestImplementation(Dependencies.compose_test_junit4)
    debugImplementation(Dependencies.compose_ui_tooling)
    debugImplementation(Dependencies.compose_test_manifest)
}