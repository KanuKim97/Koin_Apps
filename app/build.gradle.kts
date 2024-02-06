plugins {
    id(PlugIns.android_application)
    id(PlugIns.kotlin_android)
    id(PlugIns.ksp)
    id(PlugIns.dagger_hilt)
}

kotlin.jvmToolchain(ApplicationConfig.jdkVersion)

android {
    namespace = "com.example.koin_apps"
    compileSdk = ApplicationConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.Koin_Apps"
        minSdk = ApplicationConfig.minSdk
        targetSdk = ApplicationConfig.targetSdk
        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = Versions.compose
}

dependencies {
    implementation(Dependencies.androidx_core)

    implementation(project(Module.feature_home))
    implementation(project(Module.feature_choice))

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
    implementation(Dependencies.androidx_navigation)
    implementation(Dependencies.compose_navigation)
    implementation(Dependencies.hilt_compose_navigation)
    implementation(Dependencies.compose_lifecycle_viewmodel)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidx_test_junit)
    androidTestImplementation(Dependencies.espresso_core)

    androidTestImplementation(composeBoM)
    androidTestImplementation(Dependencies.compose_test_junit4)
    debugImplementation(Dependencies.compose_ui_tooling)
    debugImplementation(Dependencies.compose_test_manifest)
}