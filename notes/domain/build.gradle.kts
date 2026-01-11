plugins {
    id("com.rms.kmp.app.platforms.plugin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":notes:data"))
            }
        }
    }
}

android {
    namespace = "com.rms.notes.domain"
}
