plugins {
    id("com.rms.kmp.app.platforms.plugin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.coroutines)
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.data"
}