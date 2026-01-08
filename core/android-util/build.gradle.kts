plugins {
    id("com.rms.android-library.plugin")
}

android {
    namespace = "com.rms.android_util"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}