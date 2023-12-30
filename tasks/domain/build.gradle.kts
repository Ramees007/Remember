plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.android-hilt.plugin")
}

android {
    namespace = "com.ramees.domain"
}

dependencies {
    api(project(":tasks:data"))
    implementation(project(":core:util"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}