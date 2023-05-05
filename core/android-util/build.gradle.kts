plugins {
    id("com.rms.android-library.plugin")
}

android {
    namespace = "com.rms.android_util"

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.2.2")
}