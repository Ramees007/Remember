plugins {
    id("com.rms.kmp.app.platforms.plugin")
    alias(libs.plugins.metro)
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":core:util"))
                api(project(":shared:feature:tasks:data"))
            }
        }
    }
}

android {
    namespace = "com.rms.tasks.domain"
}