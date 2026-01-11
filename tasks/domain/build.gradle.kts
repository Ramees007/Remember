plugins {
    id("com.rms.kmp.app.platforms.plugin")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:util"))
                api(project(":tasks:data"))
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.domain"
}