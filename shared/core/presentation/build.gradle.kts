plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.androidx.lifecycle.viewmodel)
                api(libs.coroutines)
            }
        }
    }
}

android {
    namespace = "com.rms.remember.shared.core.presentation"
}
