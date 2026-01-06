plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.android-hilt.plugin")
}

android {
    namespace = "com.rms.db"
}

dependencies {
    api(libs.room.runtime)
    ksp(libs.room.compiler)
    api(libs.room.ktx)
}