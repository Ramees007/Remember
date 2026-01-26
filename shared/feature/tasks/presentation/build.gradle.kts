plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-48"
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