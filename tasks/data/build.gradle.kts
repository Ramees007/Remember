plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.android-hilt.plugin")
}

android {
    namespace = "com.rms.data"
}

dependencies {
    implementation(project(":core:db"))
    implementation(project(":core:android-util"))
}