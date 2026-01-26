plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":shared:core:presentation"))
                api(project(":shared:feature:tasks:domain"))
                implementation(project(":shared:util"))
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.presentation"
}