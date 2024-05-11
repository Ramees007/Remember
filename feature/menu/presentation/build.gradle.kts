plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.module-compose.plugin")
    id("com.rms.android-hilt.plugin")
}

android {
    namespace = "com.rms.feature.menu.presentation"
}

dependencies {
    implementation(project(":core:ui"))
}