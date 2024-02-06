object PlugIns {
    const val android_application = "com.android.application"
    const val android_library = "com.android.library"
    const val kotlin_android = "org.jetbrains.kotlin.android"
    const val kotlin_jvm = "org.jetbrains.kotlin.jvm"
    const val ksp = "com.google.devtools.ksp"
    const val dagger_hilt = "com.google.dagger.hilt.android"
}

object Dependencies {
    const val androidx_core = "androidx.core:core:${Versions.androidx_core}"

    const val androidx_navigation = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val retrofit2_convertor_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"

    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger_hilt}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger_hilt}"
    const val hilt_compose_navigation = "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}"

    const val composeBoM = "androidx.compose:compose-bom:${Versions.composeBoM}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
    const val compose_material3 = "androidx.compose.material3:material3"
    const val compose_ui = "androidx.compose.ui:ui"
    const val compose_ui_graphics = "androidx.compose.ui:ui-graphics"
    const val compose_ui_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_livedata = "androidx.compose.runtime:runtime-livedata"
    const val compose_material_icon = "androidx.compose.material:material-icons-extended"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"
    const val compose_test_manifest = "androidx.compose.ui:ui-test-manifest"
    const val compose_test_junit4 = "androidx.compose.ui:ui-test-junit4"
    const val compose_navigation = "androidx.navigation:navigation-compose:${Versions.androidx_navigation}"
    const val compose_lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_viewmodel_compose}"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidx_test_junit = "androidx.test.ext:junit:${Versions.androidx_test_junit}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}

object Versions {
    const val gradle = "8.2.1"
    const val kotlin = "1.9.10"
    const val ksp = "1.9.10-1.0.13"

    const val androidx_core = "1.12.0"
    const val androidx_navigation = "2.7.6"

    const val retrofit2 = "2.9.0"

    const val coroutine = "1.7.3"
    const val dagger_hilt = "2.50"
    const val room = "2.6.1"

    const val composeBoM = "2024.01.00"
    const val compose = "1.5.3"
    const val activity_compose = "1.8.2"
    const val hilt_navigation_compose = "1.1.0"
    const val lifecycle_viewmodel_compose = "2.7.0"

    const val junit = "4.13.2"
    const val androidx_test_junit = "1.1.5"
    const val espresso_core = "3.5.1"
}