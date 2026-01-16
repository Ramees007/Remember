plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies{
                api(libs.coroutines)
                api(project(":shared:core:db"))
            }
        }
    }
}

android {
    namespace = "com.rms.notes.data"
}