plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:core:presentation"))
                api(project(":shared:feature:notes:domain"))
            }
        }
    }
}

android {
    namespace = "com.rms.remember.shared.feature.notes.presentation"
}
