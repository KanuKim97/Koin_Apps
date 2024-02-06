plugins {
    id(PlugIns.android_library)
    id(PlugIns.kotlin_android)
    id(PlugIns.ksp)
    id(PlugIns.dagger_hilt)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.koin.choice"
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

    implementation(project(Module.core_model))
    implementation(project(Module.core_domain))
    implementation(project(Module.core_database))
    implementation(project(Module.core_designsystem))
    implementation(project(Module.core_ui))

    implementation(Dependencies.hilt)
    ksp(Dependencies.hilt_compiler)

    val composeBoM = platform(Dependencies.composeBoM)
    implementation(composeBoM)
    implementation(Dependencies.compose_activity)
    implementation(Dependencies.compose_material3)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_ui_graphics)
    implementation(Dependencies.compose_ui_preview)
    implementation(Dependencies.compose_livedata)
    implementation(Dependencies.compose_material_icon)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation(Dependencies.compose_navigation)
    implementation(Dependencies.hilt_compose_navigation)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
    androidTestImplementation(Dependencies.espresso_core)

    androidTestImplementation(composeBoM)
    androidTestImplementation(Dependencies.compose_test_junit4)
    debugImplementation(Dependencies.compose_ui_tooling)
    debugImplementation(Dependencies.compose_test_manifest)
}