plugins {
    id("com.rms.kmp.app.platforms.plugin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.coroutines)
                implementation(project(":shared:core:db"))
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.data"
}