plugins {
    id("com.rms.kmp.app.platforms.plugin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":shared:util"))
            }
        }
    }
}

android {
    namespace = "com.rms.core.util"
}
