plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.module-compose.plugin")
}

android {
    namespace = "com.rms.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    api(libs.google.material)
    api(libs.androidx.hilt.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}