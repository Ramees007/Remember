plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.android-hilt.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.rms.db"
}

dependencies {
    api(libs.room.runtime)
    kapt(libs.room.compiler)
    api(libs.room.ktx)
}

kapt {
    correctErrorTypes = true
}