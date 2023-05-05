plugins {
    id("com.rms.android-library.plugin")
    id("com.rms.android-hilt.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.rms.db"
}

dependencies {
    api("androidx.room:room-runtime:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    api("androidx.room:room-ktx:2.4.3")
}

kapt {
    correctErrorTypes = true
}