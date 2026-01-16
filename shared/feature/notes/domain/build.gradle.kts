plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:feature:notes:data"))
            }
        }
    }
}

android {
    namespace = "com.rms.notes.domain"
}
