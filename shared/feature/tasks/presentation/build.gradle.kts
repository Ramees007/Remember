plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                api(project(":shared:feature:tasks:domain"))
                implementation(project(":shared:util"))
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.presentation"
}